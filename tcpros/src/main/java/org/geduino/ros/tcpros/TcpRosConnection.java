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
import org.geduino.ros.core.api.model.Transport;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.model.SocketConnection;
import org.geduino.ros.tcpros.exception.TcpRosException;
import org.geduino.ros.tcpros.exception.TcpRosHandshakeException;
import org.geduino.ros.tcpros.exception.TcpRosHeaderSerializationException;
import org.geduino.ros.tcpros.model.ConnectionHeader;
import org.geduino.ros.tcpros.util.ConnectionHeaderUtil;
import org.geduino.ros.tcpros.util.LittleEndianUtil;

public abstract class TcpRosConnection extends SocketConnection {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosConnection.class);

	private final GlobalName callerId;
	private final URI destinationId;

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

		// Log
		LOGGER.trace("performing handshake...");

		// Handshake
		handshake();

	}

	public GlobalName getCallerId() {
		return callerId;
	}

	@Override
	public URI getDestinationId() {
		return destinationId;
	}

	@Override
	public Direction getDirection() {
		return Direction.BOTH;
	}

	@Override
	public Transport getTransport() {
		return Transport.TCPROS;
	}
	
	public int readLittleEndian() throws IOException {

		byte[] bytes = new byte[4];

		// Read bytes
		read(bytes, 4);

		// Get integer
		int integer = LittleEndianUtil.toLittleEndianInt(bytes);

		return integer;

	}

	public String readString(int length) throws IOException {

		byte[] bytes = new byte[length];

		// Read bytes
		read(bytes, length);

		// Get string
		String string = new String(bytes);

		return string;

	}

	public void writeLittleEndian(int integer) throws IOException {

		// Get little endian bytes
		byte[] bytes = LittleEndianUtil.toLittleEndianBytes(integer);

		// Write bytes
		write(bytes);

	}

	public void writeString(String string) throws IOException {

		// Get bytes
		byte[] bytes = string.getBytes();

		// Write bytes
		write(bytes);

	}

	public abstract ConnectionHeader getPublisherConnectionHeader();

	public abstract ConnectionHeader getSubscriberConnectionHeader();

	protected abstract void handshake() throws IOException,
			TcpRosHandshakeException;

	protected String getMandatoryField(ConnectionHeader connectionHeader,
			String fieldName) throws TcpRosHandshakeException {

		// Get field value
		String fieldValue = connectionHeader.get(fieldName);
		
		if (fieldValue == null) {

			// Throw exception
			throw new TcpRosHandshakeException("mandatory field " + fieldName
					+ " is missing: " + connectionHeader);

		}
		
		return fieldValue;

	}

	protected ConnectionHeader readConnectionHeader() throws IOException,
			TcpRosHeaderSerializationException {

		// Create connection header
		ConnectionHeader connectionHeader = new ConnectionHeader();

		// Init read bytes
		int readBytes = 0;

		// Log
		LOGGER.trace("reading 4 bytes header length...");

		// Get header length and total header length (including header length 4
		// bytes)
		int headerLength = readLittleEndian();
		int totalHeaderLength = headerLength + 4;

		// Increase read bytes
		readBytes += 4;

		// Log
		LOGGER.trace("reading " + headerLength + " header bytes...");

		while (readBytes < totalHeaderLength) {

			// Log
			LOGGER.trace("reading 4 bytes field length...");

			// Get next field length
			int fieldLength = readLittleEndian();

			// Increase read bytes
			readBytes += 4;

			// Log
			LOGGER.trace("reading " + fieldLength + " bytes field...");

			// Get next field string
			String fieldString = readString(fieldLength);

			// Increase read bytes
			readBytes += fieldLength;

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
				throw new TcpRosHeaderSerializationException(
						"field string must contain equals: " + fieldString);

			}

			// Log
			LOGGER.trace("read " + readBytes + " of " + totalHeaderLength);

		}

		return connectionHeader;

	}

	protected void write(ConnectionHeader connectionHeader) throws IOException {

		// Get connection header length and total header length (including
		// header length 4
		// bytes)
		int headerLength = ConnectionHeaderUtil
				.getHeaderLength(connectionHeader);
		int totalHeaderLength = headerLength + 4;

		// Init wrote bytes
		int wroteBytes = 0;

		// Log
		LOGGER.trace("writing 4 bytes header length...");

		// Write header length
		writeLittleEndian(headerLength);

		// Increase wrote bytes
		wroteBytes += 4;

		// Get fields set
		Set<Map.Entry<String, String>> fieldSet = connectionHeader.fieldSet();

		for (Iterator<Map.Entry<String, String>> iterator = fieldSet.iterator(); iterator
				.hasNext();) {

			// Get field
			Map.Entry<String, String> field = iterator.next();

			// Get field length
			int fieldLength = ConnectionHeaderUtil.getFieldLength(field);

			// Log
			LOGGER.trace("writing 4 bytes field length...");

			// Write field length
			writeLittleEndian(fieldLength);

			// Increase wrote bytes
			wroteBytes += 4;

			// Log
			LOGGER.trace("writing " + fieldLength + " bytes field...");

			// Write key
			writeString(field.getKey());
			writeString("=");
			writeString(field.getValue());

			// Increase wrote bytes
			wroteBytes += fieldLength;

			// Log
			LOGGER.trace("wrote " + wroteBytes + " of " + totalHeaderLength);

		}

	}

}
