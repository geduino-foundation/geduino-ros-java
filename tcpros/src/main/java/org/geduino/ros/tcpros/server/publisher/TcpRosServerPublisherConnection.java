package org.geduino.ros.tcpros.server.publisher;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;

import org.apache.log4j.Logger;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.Direction;
import org.geduino.ros.core.api.model.PublisherConnectionData;
import org.geduino.ros.core.api.model.Transport;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.exception.RosTransportSerializationException;
import org.geduino.ros.core.transport.model.MessageWriter;
import org.geduino.ros.core.transport.model.PublisherConnection;
import org.geduino.ros.tcpros.TcpRosConnection;
import org.geduino.ros.tcpros.exception.TcpRosException;
import org.geduino.ros.tcpros.exception.TcpRosHandshakeException;
import org.geduino.ros.tcpros.model.ConnectionHeader;

public class TcpRosServerPublisherConnection<M extends Message> extends
		TcpRosConnection implements PublisherConnection<M> {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosServerPublisherConnection.class);

	private final GlobalName topic;
	private final MessageDetails<M> messageDetails;

	public TcpRosServerPublisherConnection(GlobalName callerId,
			GlobalName topic, MessageDetails<M> messageDetails, Socket socket)
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
	protected void handshake() throws IOException, TcpRosHandshakeException {

		// Log
		LOGGER.trace("reading subscriber connection header...");

		try {

			// Read connection header
			ConnectionHeader subscriberConnectionHeader = readConnectionHeader();

			// Get fields
			getMandatoryField(subscriberConnectionHeader,
					ConnectionHeader.TOPIC, topic.toString());
			getMandatoryField(subscriberConnectionHeader,
					ConnectionHeader.MESSAGE_DEFINITION);
			getMandatoryField(subscriberConnectionHeader,
					ConnectionHeader.CALLER_ID);
			getMandatoryField(subscriberConnectionHeader,
					ConnectionHeader.MD5_SUM, messageDetails.getMessageMd5Sum());
			getMandatoryField(subscriberConnectionHeader,
					ConnectionHeader.TYPE, messageDetails.getMessageName()
							.toString());
			String tcpNoDelay = subscriberConnectionHeader
					.get(ConnectionHeader.TCP_NO_DELAY);

			if ("1".equals(tcpNoDelay)) {

				try {

					// Set tcp no delay
					setTcpNoDelay(true);

				} catch (SocketException ex) {

					// Log
					LOGGER.warn(
							"ignoring subscriber request to set tcpnodelay", ex);

				}

			}

			// Create publisher connection header
			ConnectionHeader publisherConnectionHeader = new ConnectionHeader();
			publisherConnectionHeader.put(ConnectionHeader.CALLER_ID,
					getCallerId().toString());
			publisherConnectionHeader.put(ConnectionHeader.MD5_SUM,
					messageDetails.getMessageMd5Sum());
			publisherConnectionHeader.put(ConnectionHeader.TYPE, messageDetails
					.getMessageName().toString());
			publisherConnectionHeader.put(ConnectionHeader.LATCHING, "1");

			// Write publisher connection header
			write(publisherConnectionHeader);

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
	public PublisherConnectionData getPublisherConnectionData() {

		// Get publisher connection data
		String connectionId = getConnectionId();
		int byteSent = getByteSent();
		int numSent = getNumSent();
		boolean connected = isConnected();

		// Create publisher connection data
		PublisherConnectionData publisherConnectionData = new PublisherConnectionData(
				connectionId, byteSent, numSent, connected);

		return publisherConnectionData;

	}

	@Override
	public MessageWriter<M> getMessageWriter() {
		// TODO Auto-generated method stub
		return null;
	}

}
