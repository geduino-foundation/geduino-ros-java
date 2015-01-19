package org.geduino.ros.tcpros.server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;

import org.apache.log4j.Logger;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.Direction;
import org.geduino.ros.core.api.model.PublisherConnectionData;
import org.geduino.ros.core.api.model.Transport;
import org.geduino.ros.core.exception.RosRuntimeException;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.core.transport.model.MessageReader;
import org.geduino.ros.core.transport.model.MessageWriter;
import org.geduino.ros.core.transport.model.PublisherConnection;
import org.geduino.ros.core.transport.model.ServiceConnection;
import org.geduino.ros.tcpros.TcpRosConnection;
import org.geduino.ros.tcpros.exception.TcpRosException;
import org.geduino.ros.tcpros.exception.TcpRosHandshakeException;
import org.geduino.ros.tcpros.model.ConnectionHeader;

public class TcpRosServerConnection<T extends Message, K extends Message>
		extends TcpRosConnection implements PublisherConnection<K>,
		ServiceConnection<T, K> {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosServerConnection.class);

	private ConnectionHeader subscriberConnectionHeader;
	private ConnectionHeader publisherConnectionHeader;

	public TcpRosServerConnection(GlobalName callerdId, Socket socket)
			throws TcpRosException, IOException {

		super(callerdId, socket);

		// Log
		LOGGER.trace("performing handshake...");

		// Handshake
		handshake();

	}

	public boolean isPublisherConnection() {

		// Get is publisher
		boolean isPublisher = getSubscriberConnectionHeader().containsKey(
				ConnectionHeader.TOPIC);

		return isPublisher;

	}

	public boolean isServiceConnection() {

		// Get is service
		boolean isService = getSubscriberConnectionHeader().containsKey(
				ConnectionHeader.SERVICE);

		return isService;

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
	public String getServiceName() {

		if (isServiceConnection()) {

			// Get service name
			String serviceName = getSubscriberConnectionHeader().get(
					ConnectionHeader.SERVICE);

			return serviceName;

		} else {

			// Throw exception
			throw new RosRuntimeException(
					"service name cannot be retrieved for non service connection");

		}

	}

	@Override
	public BusInfo getBusInfo() {

		if (isPublisherConnection()) {

			// Get bus info data
			String connectionId = getConnectionId();
			URI destinationId = getDestinationId();
			Direction direction = getDirection();
			Transport transport = getTransport();
			String topicNameString = getSubscriberConnectionHeader().get(
					ConnectionHeader.TOPIC);
			boolean connected = isConnected();

			// Get topic name
			GlobalName topicName = (GlobalName) Name.parseName(topicNameString);

			// Create bus info
			BusInfo busInfo = new BusInfo(connectionId, destinationId,
					direction, transport, topicName, connected);

			return busInfo;

		} else {

			// Throw exception
			throw new RosRuntimeException(
					"bus info cannot be retrieved for non publisher connection");

		}

	}

	@Override
	public PublisherConnectionData getPublisherConnectionData() {

		if (isPublisherConnection()) {

			// Get publisher connection data
			String connectionId = getConnectionId();
			int byteSent = getByteSent();
			int numSent = getNumSent();
			boolean connected = isConnected();

			// Create publisher connection data
			PublisherConnectionData publisherConnectionData = new PublisherConnectionData(
					connectionId, byteSent, numSent, connected);

			return publisherConnectionData;

		} else {

			// Throw exception
			throw new RosRuntimeException(
					"publisher connection data cannot be retrieved for non publisher connection");

		}

	}

	@Override
	protected void handshake() throws TcpRosHandshakeException, IOException {

		// Log
		LOGGER.trace("reading subscriber connection header...");

		// Read connection header
		subscriberConnectionHeader = readConnectionHeader();

		// Get topic and service field
		String topic = subscriberConnectionHeader.get(ConnectionHeader.TOPIC);
		String service = subscriberConnectionHeader
				.get(ConnectionHeader.SERVICE);

		if (topic != null && service == null) {

			// Log
			LOGGER.trace("handshake topic connection...");

			// Handshake topic connection
			handshakeTopicConnection();

		} else if (topic == null && service != null) {

			// Log
			LOGGER.trace("handshake service connection...");

			// Handshake service connection
			handshakeServiceConnection();

		} else if (topic != null && service != null) {

			// Throw exception
			throw new TcpRosHandshakeException(
					"topic and service fields cannot be both not null");

		} else {

			// Throw exception
			throw new TcpRosHandshakeException(
					"one between topic and service fields must be not null");

		}

	}

	private void handshakeTopicConnection() throws TcpRosHandshakeException,
			IOException {

		// Get fields
		getMandatoryField(subscriberConnectionHeader,
				ConnectionHeader.MESSAGE_DEFINITION);
		getMandatoryField(subscriberConnectionHeader,
				ConnectionHeader.CALLER_ID);
		String md5sum = getMandatoryField(subscriberConnectionHeader,
				ConnectionHeader.MD5_SUM);
		String type = getMandatoryField(subscriberConnectionHeader,
				ConnectionHeader.TYPE);
		String tcpNoDelay = subscriberConnectionHeader
				.get(ConnectionHeader.TCP_NO_DELAY);

		if ("1".equals(tcpNoDelay)) {

			try {

				// Set tcp no delay
				setTcpNoDelay(true);

			} catch (SocketException ex) {

				// Log
				LOGGER.warn("ignoring subscriber request to set tcpnodelay", ex);

			}

		}

		// Create publisher connection header
		publisherConnectionHeader = new ConnectionHeader();
		publisherConnectionHeader.put(ConnectionHeader.CALLER_ID, getCallerId()
				.toString());
		publisherConnectionHeader.put(ConnectionHeader.MD5_SUM, md5sum);
		publisherConnectionHeader.put(ConnectionHeader.TYPE, type);
		publisherConnectionHeader.put(ConnectionHeader.LATCHING, "1");

		// Write publisher connection header
		write(publisherConnectionHeader);

	}

	private void handshakeServiceConnection() throws TcpRosHandshakeException,
			IOException {

		// Get fields
		getMandatoryField(subscriberConnectionHeader,
				ConnectionHeader.CALLER_ID);
		getMandatoryField(subscriberConnectionHeader, ConnectionHeader.MD5_SUM);
		getMandatoryField(subscriberConnectionHeader, ConnectionHeader.TYPE);

		// Create publisher connection header
		publisherConnectionHeader = new ConnectionHeader();
		publisherConnectionHeader.put(ConnectionHeader.CALLER_ID, getCallerId()
				.toString());

		// Write publisher connection header
		write(publisherConnectionHeader);

	}

	@Override
	public String toString() {

		// Init string buffer
		StringBuffer stringBuffer = new StringBuffer(
				"TcpRosServerConnection [id=" + getConnectionId()
						+ ", destinationId=" + getDestinationId()
						+ ", connected=" + isConnected() + ", type=");

		if (isPublisherConnection()) {

			// Append type
			stringBuffer.append("publisher, topic="
					+ getBusInfo().getTopicName() + "]");

		} else if (isServiceConnection()) {

			// Append type
			stringBuffer.append("service, service=" + getServiceName() + "]");

		} else {

			// Append type
			stringBuffer.append("unknow]");

		}

		return stringBuffer.toString();

	}

	@Override
	public MessageReader<T> getMessageReader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageWriter<K> getMessageWriter() {
		// TODO Auto-generated method stub
		return null;
	}

}
