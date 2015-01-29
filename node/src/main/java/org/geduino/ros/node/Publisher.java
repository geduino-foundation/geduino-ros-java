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
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.model.ConnectionListener;
import org.geduino.ros.core.transport.model.PublisherConnection;
import org.geduino.ros.node.exception.RosPublisherException;
import org.geduino.ros.tcpros.server.exception.TcpRosServerException;
import org.geduino.ros.tcpros.server.publisher.TcpRosServerPublisher;
import org.geduino.ros.tcpros.server.publisher.TcpRosServerPublisherConfig;
import org.geduino.ros.tcpros.server.publisher.TcpRosServerPublisherConnection;
import org.geduino.ros.tcpros.server.publisher.TcpRosServerPublisherConnectionFactory;

public class Publisher<M extends Message> implements
		ConnectionListener<TcpRosServerPublisherConnection<M>> {

	private static final Logger LOGGER = Logger.getLogger(Publisher.class);

	private final GlobalName topic;
	private final MessageDetails<M> messageDetails;

	private final Set<TcpRosServerPublisher<M>> tcpRosServerPublishers;

	private final List<PublisherConnection<M>> publisherConnections;

	public Publisher(GlobalName topic, MessageDetails<M> messageDetails) {

		this.topic = topic;
		this.messageDetails = messageDetails;

		tcpRosServerPublishers = new HashSet<TcpRosServerPublisher<M>>();

		publisherConnections = new ArrayList<PublisherConnection<M>>();

	}

	public GlobalName getTopic() {
		return topic;
	}

	public MessageDetails<M> getMessageDetails() {
		return messageDetails;
	}

	public Set<BusInfo> getBusInfos() {

		// Create bus info set
		Set<BusInfo> busInfoSet = new HashSet<BusInfo>();

		for (Iterator<PublisherConnection<M>> iterator = publisherConnections
				.iterator(); iterator.hasNext();) {

			// Get next publisher connection
			PublisherConnection<M> publisherConnection = iterator.next();

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

		for (Iterator<PublisherConnection<M>> iterator = publisherConnections
				.iterator(); iterator.hasNext();) {

			// Get next publisher connection
			PublisherConnection<M> publisherConnection = iterator.next();

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

	protected void stop() {

		// Log
		LOGGER.debug("stopping tcp ros servers...");

		for (Iterator<TcpRosServerPublisher<M>> iterator = tcpRosServerPublishers
				.iterator(); iterator.hasNext();) {

			// Get next tcp ros server publisher
			TcpRosServerPublisher<M> tcpRosServerPublisher = iterator.next();

			// Log
			LOGGER.trace("stopping tcp ros server publisher: "
					+ tcpRosServerPublisher);

			// Stop tcp ros server publisher
			tcpRosServerPublisher.stop();

		}

		// Log
		LOGGER.debug("closing publisher connections...");

		for (Iterator<PublisherConnection<M>> iterator = publisherConnections
				.iterator(); iterator.hasNext();) {

			// Get next publisher connection
			PublisherConnection<M> publisherConnection = iterator.next();

			// Log
			LOGGER.trace("closing publisher connection: " + publisherConnection);

			try {
				
				// Close connection
				publisherConnection.close();

			} catch (Exception ex) {

				// Log
				LOGGER.error("could not close publisher connection", ex);

			}
			
		}

	}

	Protocol newConnection(GlobalName nodeName, URI nodeUri,
			Set<ProtocolType> protocolTypes) throws RosPublisherException {

		if (protocolTypes.contains(ProtocolType.TCPROS)) {

			// Log
			LOGGER.trace("create new tcpros server...");

			// Create server config
			TcpRosServerPublisherConfig<M> tcpRosServerPublisherConfig = new TcpRosServerPublisherConfig<M>(
					nodeName, 0, 1,
					new TcpRosServerPublisherConnectionFactory<M>(topic,
							messageDetails));
			tcpRosServerPublisherConfig.addConnectionListener(this);

			// Create tcp ros server publisher
			TcpRosServerPublisher<M> tcpRosServerPublisher = new TcpRosServerPublisher<M>(
					tcpRosServerPublisherConfig);

			try {

				// Start tcp ros server publisher
				tcpRosServerPublisher.start();

				// Add to tcp ros server publishers
				tcpRosServerPublishers.add(tcpRosServerPublisher);

				// Get tcp ros server port
				int port = tcpRosServerPublisher.getEffectivePort();

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

	public void publish(M message) {

		for (Iterator<PublisherConnection<M>> iterator = publisherConnections
				.iterator(); iterator.hasNext();) {

			// Get next publisher connection
			PublisherConnection<M> publisherConnection = iterator.next();

			try {

				// Write message
				publisherConnection.getMessageWriter().write(message);

			} catch (IOException ex) {

				// Log
				LOGGER.error("could not publish message: " + message
						+ " to connection: " + publisherConnection, ex);

			} catch (RosMessageSerializationException ex) {

				// Log
				LOGGER.error("could not publish message: " + message
						+ " to connection: " + publisherConnection, ex);

			}

		}

	}

	@Override
	public void incomingConnection(
			TcpRosServerPublisherConnection<M> tcpRosServerPublisherConnection) {

		// Add publisher connection
		publisherConnections.add(tcpRosServerPublisherConnection);

	}

	@Override
	public String toString() {
		return "Publisher [topic=" + topic + ", type="
				+ messageDetails.getMessageName() + "]";
	}

}
