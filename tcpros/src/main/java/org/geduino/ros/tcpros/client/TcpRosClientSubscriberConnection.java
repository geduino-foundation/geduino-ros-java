package org.geduino.ros.tcpros.client;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;

import org.apache.log4j.Logger;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.Direction;
import org.geduino.ros.core.api.model.SubscriberConnectionData;
import org.geduino.ros.core.api.model.Transport;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.exception.RosTransportSerializationException;
import org.geduino.ros.core.transport.model.MessageReader;
import org.geduino.ros.core.transport.model.SubscriberConnection;
import org.geduino.ros.tcpros.TcpRosConnection;
import org.geduino.ros.tcpros.exception.TcpRosException;
import org.geduino.ros.tcpros.exception.TcpRosHandshakeException;
import org.geduino.ros.tcpros.model.ConnectionHeader;

public class TcpRosClientSubscriberConnection<T extends Message> extends
		TcpRosConnection implements SubscriberConnection<T> {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosClientSubscriberConnection.class);

	private final GlobalName topic;
	private final MessageDetails<T> messageDetails;

	public TcpRosClientSubscriberConnection(GlobalName callerId, Socket socket,
			GlobalName topic, MessageDetails<T> messageDetails)
			throws TcpRosException, IOException {

		super(callerId, socket);

		this.topic = topic;
		this.messageDetails = messageDetails;

		// Log
		LOGGER.trace("performing handshake...");

		// Handshake
		handshake();

	}

	@Override
	public BusInfo getBusInfo() {

		// Get bus info data
		String connectionId = getConnectionId();
		URI destinationId = getDestinationId();
		Direction direction = getDirection();
		Transport transport = getTransport();
		boolean connected = isConnected();

		// Create bus info
		BusInfo busInfo = new BusInfo(connectionId, destinationId, direction,
				transport, topic, connected);

		return busInfo;

	}

	@Override
	public SubscriberConnectionData getSubscriberConnectionData() {

		// Get subscriber connection data
		String connectionId = getConnectionId();
		int byteReceived = getByteReceived();
		boolean connected = isConnected();

		// Create subscriber connection data
		SubscriberConnectionData subscriberConnectionData = new SubscriberConnectionData(
				connectionId, byteReceived, -1, connected);

		return subscriberConnectionData;

	}

	@Override
	protected void handshake() throws IOException, TcpRosHandshakeException {

		try {

			// Create connection header
			ConnectionHeader subscriberConnectionHeader = new ConnectionHeader();
			subscriberConnectionHeader.put(ConnectionHeader.CALLER_ID,
					getCallerId().toString());
			subscriberConnectionHeader.put(ConnectionHeader.TOPIC,
					topic.toString());
			subscriberConnectionHeader.put(ConnectionHeader.MESSAGE_DEFINITION,
					messageDetails.getMessageDefinition());
			subscriberConnectionHeader.put(ConnectionHeader.MD5_SUM,
					messageDetails.getMessageMd5Sum());
			subscriberConnectionHeader.put(ConnectionHeader.TYPE,
					messageDetails.getMessageName().toString());

			// Log
			LOGGER.trace("sending subscriber connection header...");

			// Write connection header
			write(subscriberConnectionHeader);

			// Read connection header
			ConnectionHeader publisherConnectionHeader = readConnectionHeader();

			if (publisherConnectionHeader.containsKey(ConnectionHeader.ERROR)) {

				// Get error
				String error = publisherConnectionHeader
						.get(ConnectionHeader.ERROR);

				// Throw exception
				throw new TcpRosHandshakeException(error);

			}

			// Read connection header data
			getMandatoryField(publisherConnectionHeader,
					ConnectionHeader.MD5_SUM, messageDetails.getMessageMd5Sum());
			getMandatoryField(publisherConnectionHeader, ConnectionHeader.TYPE,
					messageDetails.getMessageName().toString());

		} catch (TcpRosHandshakeException ex) {

			try {

				// Handle failed handshake
				handleFailedHandshake(ex);

			} catch (IOException ex2) {

				// Log
				LOGGER.error("could not notify handshake error to destination",
						ex2);

			} catch (RosTransportSerializationException ex2) {

				// Log
				LOGGER.error("could not notify handshake error to destination",
						ex2);

			}

			// Throw original exception
			throw ex;

		} catch (RosTransportSerializationException ex) {

			// Throw exception
			throw new TcpRosHandshakeException("handshake failed", ex);

		}

	}

	@Override
	public MessageReader<T> getMessageReader() {
		return new MessageReader<T>() {

			@Override
			public T read() throws IOException,
					RosTransportSerializationException {

				return null;
			}

		};

	}

	@Override
	public String toString() {

		return "TcpRosClientSubscriberConnection [id=" + getConnectionId()
				+ ", destinationId=" + getDestinationId() + ", connected="
				+ isConnected() + "]";

	}

}
