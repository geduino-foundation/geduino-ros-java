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
import org.geduino.ros.core.transport.model.MessageReader;
import org.geduino.ros.core.transport.model.SubscriberConnection;
import org.geduino.ros.tcpros.TcpRosConnection;
import org.geduino.ros.tcpros.exception.TcpRosException;
import org.geduino.ros.tcpros.exception.TcpRosHandshakeException;
import org.geduino.ros.tcpros.model.ConnectionHeader;

public class TcpRosClientSubscriberConnection<T extends Message> extends TcpRosConnection
		implements SubscriberConnection<T> {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosClientSubscriberConnection.class);

	private final GlobalName topic;
	private final MessageDetails<T> messageDetails;

	private ConnectionHeader subscriberConnectionHeader;
	private ConnectionHeader publisherConnectionHeader;

	public TcpRosClientSubscriberConnection(GlobalName callerId, Socket socket, GlobalName topic,
			MessageDetails<T> messageDetails)
			throws TcpRosException, IOException {

		super(callerId, socket);

		this.topic = topic;
		this.messageDetails = messageDetails;

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
	public ConnectionHeader getPublisherConnectionHeader() {
		return publisherConnectionHeader;
	}

	@Override
	public ConnectionHeader getSubscriberConnectionHeader() {
		return subscriberConnectionHeader;
	}

	@Override
	protected void handshake() throws IOException, TcpRosHandshakeException {

		// Create connection header
		subscriberConnectionHeader = new ConnectionHeader();
		subscriberConnectionHeader.put(ConnectionHeader.CALLER_ID,
				getCallerId().toString());
		subscriberConnectionHeader
				.put(ConnectionHeader.TOPIC, topic.toString());
		subscriberConnectionHeader.put(ConnectionHeader.MESSAGE_DEFINITION,
				messageDetails.getMessageDefinition());
		subscriberConnectionHeader.put(ConnectionHeader.MD5_SUM,
				messageDetails.getMessageMd5Sum());
		subscriberConnectionHeader.put(ConnectionHeader.TYPE, messageDetails
				.getMessageName().toString());

		// Log
		LOGGER.trace("sending subscriber connection header...");

		// Write connection header
		write(subscriberConnectionHeader);

		// Read connection header
		publisherConnectionHeader = readConnectionHeader();

		// Read connection header data
		String md5sum = getMandatoryField(publisherConnectionHeader,
				ConnectionHeader.MD5_SUM);
		String type = getMandatoryField(publisherConnectionHeader,
				ConnectionHeader.TYPE);

		if (!md5sum.equals(messageDetails.getMessageMd5Sum())) {

			// Throw exception
			throw new TcpRosHandshakeException("expected message md5sum: "
					+ messageDetails.getMessageMd5Sum() + " but was: " + md5sum);

		}

		if (!type.equals(messageDetails.getMessageName().toString())) {

			// Throw exception
			throw new TcpRosHandshakeException("expected message type: "
					+ messageDetails.getMessageName() + " but was: " + type);

		}

	}

	@Override
	public MessageReader<T> getMessageReader() {
		// TODO Auto-generated method stub
		return null;
	}

}
