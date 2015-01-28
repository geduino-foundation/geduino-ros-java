package org.geduino.ros.tcpros;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.geduino.ros.core.api.model.Direction;
import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.core.api.model.TcpRosProtocol;
import org.geduino.ros.core.api.model.Transport;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.tcpros.exception.TcpRosException;
import org.geduino.ros.tcpros.exception.TcpRosHandshakeException;
import org.geduino.ros.tcpros.model.ConnectionHeader;
import org.geduino.ros.tcpros.util.ConnectionHeaderUtil;

public abstract class TcpRosConnection extends SocketConnection {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosConnection.class);

	private final GlobalName callerId;
	private final URI destinationId;

	protected static String getMandatoryField(
			ConnectionHeader connectionHeader, String fieldName)
			throws TcpRosHandshakeException {

		// Get field value
		String fieldValue = connectionHeader.get(fieldName);

		if (fieldValue == null) {

			// Throw exception
			throw new TcpRosHandshakeException("mandatory field " + fieldName
					+ " is missing: " + connectionHeader);

		}

		return fieldValue;

	}

	protected static String getMandatoryField(
			ConnectionHeader connectionHeader, String fieldName,
			String expectedValue) throws TcpRosHandshakeException {

		// Get field value
		String fieldValue = getMandatoryField(connectionHeader, fieldName);

		if (!expectedValue.equals(fieldValue)) {

			// Throw exception
			throw new TcpRosHandshakeException("field: " + fieldName
					+ " expected value: " + expectedValue + " but was: "
					+ fieldValue);

		}

		return fieldValue;

	}

	public TcpRosConnection(GlobalName callerId, Socket socket)
			throws TcpRosException, IOException {

		super(socket);

		this.callerId = callerId;

		// Get destination host name and port
		String destinationHostName = socket.getInetAddress().getHostName();
		int destinationPort = socket.getPort();

		try {

			// Get destination id
			destinationId = new URI("tcp", null, destinationHostName,
					destinationPort, null, null, null);

		} catch (URISyntaxException ex) {

			// Throw exception
			throw new TcpRosException("an error occurs getting destination id",
					ex);

		}

	}

	@Override
	public Protocol getConnectionProtocol() {

		// Get protocol
		Protocol protocol = new TcpRosProtocol(destinationId.getHost(),
				destinationId.getPort());

		return protocol;

	}

	public GlobalName getCallerId() {
		return callerId;
	}

	public URI getDestinationId() {
		return destinationId;
	}

	public Direction getDirection() {
		return Direction.BOTH;
	}

	public Transport getTransport() {
		return Transport.TCPROS;
	}

	protected abstract void handshake() throws IOException,
			TcpRosHandshakeException;

	protected void handleFailedHandshake(TcpRosHandshakeException ex)
			throws IOException, RosMessageSerializationException {

		// Create publisher connection header
		ConnectionHeader publisherConnectionHeader = new ConnectionHeader();
		publisherConnectionHeader.put(ConnectionHeader.ERROR, ex.getMessage());

		// Write publisher connection header
		write(publisherConnectionHeader);

	}

	protected ConnectionHeader readConnectionHeader() throws IOException,
			RosMessageSerializationException {

		// Get data reader
		InputStreamDataReader dataReader = getDataReader();

		// Create connection header
		ConnectionHeader connectionHeader = new ConnectionHeader();

		// Get header length and total header length (including header length 4
		// bytes)
		int headerLength = (int) dataReader.readUInt32();

		// Log
		LOGGER.trace("reading " + headerLength + " header bytes...");

		try {

			// Reset byte limit
			dataReader.resetByteLimit(headerLength);

			while (dataReader.getByteLimit() > 0) {

				// Get next field length
				int fieldLength = dataReader.readInt32();

				// Get next field string
				String fieldString = dataReader.readString(fieldLength);

				// Get equals index
				int equalsIndex = fieldString.indexOf("=");

				if (equalsIndex != -1) {

					// Get key and value
					String key = fieldString.substring(0, equalsIndex);
					String value = fieldString.substring(equalsIndex + 1);

					// Add field
					connectionHeader.put(key, value);

				} else {

					// Throw exception
					throw new RosMessageSerializationException(
							"field string must contain equals: " + fieldString);

				}

			}

			return connectionHeader;

		} finally {

			// Reset byte limit
			dataReader.resetByteLimit();

		}

	}

	protected void write(ConnectionHeader connectionHeader) throws IOException,
			RosMessageSerializationException {

		// Get data writer
		DataWriter dataWriter = getDataWriter();

		// Get connection header length and total header length (including
		// header length 4
		// bytes)
		int headerLength = ConnectionHeaderUtil
				.getHeaderLength(connectionHeader);

		// Write header length
		dataWriter.writeInt32(headerLength);

		// Log
		LOGGER.trace("writing " + headerLength + " header bytes...");

		// Get fields set
		Set<Map.Entry<String, String>> fieldSet = connectionHeader.fieldSet();

		for (Iterator<Map.Entry<String, String>> iterator = fieldSet.iterator(); iterator
				.hasNext();) {

			// Get field
			Map.Entry<String, String> field = iterator.next();

			// Get field length
			int fieldLength = ConnectionHeaderUtil.getFieldLength(field);

			// Write field length
			dataWriter.writeInt32(fieldLength);

			// Write key
			dataWriter.writeString(field.getKey());
			dataWriter.writeString("=");
			dataWriter.writeString(field.getValue());

		}

	}

}
