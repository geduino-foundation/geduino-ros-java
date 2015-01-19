package org.geduino.ros.node;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.core.api.model.PublisherConnectionData;
import org.geduino.ros.core.api.model.PublisherStat;
import org.geduino.ros.core.api.model.TcpRosProtocol;
import org.geduino.ros.core.exception.RosRuntimeException;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.exception.RosTransportSerializationException;
import org.geduino.ros.core.transport.model.ConnectionListener;
import org.geduino.ros.core.transport.model.PublisherConnection;
import org.geduino.ros.core.transport.model.ServiceConnection;
import org.geduino.ros.node.exception.RosPublisherException;
import org.geduino.ros.tcpros.server.TcpRosServer;
import org.geduino.ros.tcpros.server.exception.TcpRosServerException;

public class Publisher<T extends Message> implements
		ConnectionListener<Message, T> {

	private static final Logger LOGGER = Logger.getLogger(Publisher.class);

	private final GlobalName topic;
	private final MessageDetails<T> messageDetails;

	private final List<PublisherConnection<T>> publisherConnections;

	public Publisher(GlobalName topic, MessageDetails<T> messageDetails) {

		this.topic = topic;
		this.messageDetails = messageDetails;

		publisherConnections = new ArrayList<PublisherConnection<T>>();

	}

	public GlobalName getTopic() {
		return topic;
	}

	public MessageDetails<T> getMessageDetails() {
		return messageDetails;
	}

	public Set<BusInfo> getBusInfos() {

		// Create bus info set
		Set<BusInfo> busInfoSet = new HashSet<BusInfo>();

		for (Iterator<PublisherConnection<T>> iterator = publisherConnections
				.iterator(); iterator.hasNext();) {

			// Get next publisher connection
			PublisherConnection<T> publisherConnection = iterator.next();

			// Get bus info
			BusInfo busInfo = publisherConnection.getBusInfo();

			busInfoSet.add(busInfo);

		}

		return busInfoSet;

	}

	public PublisherStat getPublisherStat() {

		int sentData = 0;

		// Create publisher connection data set
		Set<PublisherConnectionData> publisherConnectionDatas = new HashSet<PublisherConnectionData>();

		for (Iterator<PublisherConnection<T>> iterator = publisherConnections
				.iterator(); iterator.hasNext();) {

			// Get next publisher connection
			PublisherConnection<T> publisherConnection = iterator.next();

			// Get publisher connection data
			PublisherConnectionData publisherConnectionData = publisherConnection
					.getPublisherConnectionData();

			publisherConnectionDatas.add(publisherConnectionData);

			// Increase sent data
			sentData += publisherConnectionData.getByteSent();

		}

		// Create publisher stat
		PublisherStat publisherStat = new PublisherStat(topic, sentData,
				publisherConnectionDatas);

		return publisherStat;

	}

	Protocol newConnection(GlobalName nodeName, URI nodeUri,
			Set<ProtocolType> protocolTypes) throws RosPublisherException {

		if (protocolTypes.contains(ProtocolType.TCPROS)) {

			// Log
			LOGGER.trace("create new tcpros server...");

			// Create tcp ros server
			TcpRosServer<Message, T> tcpRosServer = new TcpRosServer<Message, T>(
					nodeName);
			tcpRosServer.getTcpRosServerConfig().addConnectionListener(this);

			try {

				// Start tcp ros server
				tcpRosServer.start();

				// Get tcp ros server port
				int port = tcpRosServer.getEffectivePort();

				// Create tcp ros protocol
				TcpRosProtocol tcpRosProtocol = new TcpRosProtocol(
						nodeUri.getHost(), port);

				return tcpRosProtocol;

			} catch (TcpRosServerException ex) {

				// Throw exception
				throw new RosPublisherException(
						"could not start tcpros server", ex);

			}

		} else {

			// Throw exception
			throw new RosPublisherException("unsupported protocol types: "
					+ protocolTypes);

		}

	}

	public void publish(T message) {

		for (Iterator<PublisherConnection<T>> iterator = publisherConnections
				.iterator(); iterator.hasNext();) {

			// Get next publisher connection
			PublisherConnection<T> publisherConnection = iterator.next();

			try {

				// Write message
				publisherConnection.getMessageWriter().write(message);

			} catch (IOException ex) {

				// Log
				LOGGER.error("could not publish message: " + message
						+ " to connection: " + publisherConnection, ex);

			} catch (RosTransportSerializationException ex) {

				// Log
				LOGGER.error("could not publish message: " + message
						+ " to connection: " + publisherConnection, ex);

			}

		}

	}

	@Override
	public void incomingPublisherConnection(
			PublisherConnection<T> publisherConnection) {

		// Add publisher connection
		publisherConnections.add(publisherConnection);

	}

	@Override
	public void incomingServiceConnection(
			ServiceConnection<Message, T> serviceConnection) {

		// Throw exception
		throw new RosRuntimeException(
				"service connectis not supported by publisher");

	}

	@Override
	public String toString() {
		return "Publisher [topic=" + topic + ", type="
				+ messageDetails.getMessageName() + "]";
	}

}
