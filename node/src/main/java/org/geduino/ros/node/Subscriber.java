package org.geduino.ros.node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.core.api.model.SubscribeStat;
import org.geduino.ros.core.api.model.SubscriberConnectionData;
import org.geduino.ros.core.api.model.TcpRosProtocol;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.model.SubscriberConnection;
import org.geduino.ros.node.exception.RosSubscriberException;
import org.geduino.ros.tcpros.client.TcpRosClient;
import org.geduino.ros.tcpros.exception.TcpRosException;

public class Subscriber<T extends Message> {

	private static final Logger LOGGER = Logger.getLogger(Subscriber.class);

	private final GlobalName topic;
	private final MessageDetails<T> messageDetails;
	private final SubscriberListener<T> subscriberListener;

	private final List<SubscriberConnection<T>> subscriberConnections;

	public Subscriber(GlobalName topic, MessageDetails<T> messageDetails,
			SubscriberListener<T> subscriberListener) {

		this.topic = topic;
		this.messageDetails = messageDetails;
		this.subscriberListener = subscriberListener;

		subscriberConnections = new ArrayList<SubscriberConnection<T>>();

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

		for (Iterator<SubscriberConnection<T>> iterator = subscriberConnections
				.iterator(); iterator.hasNext();) {

			// Get next subscriber connection
			SubscriberConnection<T> subscriberConnection = iterator.next();

			// Get bus info
			BusInfo busInfo = subscriberConnection.getBusInfo();

			busInfoSet.add(busInfo);

		}

		return busInfoSet;

	}

	public SubscribeStat getSubscriberStat() {

		// Create subscriber connection data set
		Set<SubscriberConnectionData> subscriberConnectionDatas = new HashSet<SubscriberConnectionData>();

		for (Iterator<SubscriberConnection<T>> iterator = subscriberConnections
				.iterator(); iterator.hasNext();) {

			// Get next subscriber connection
			SubscriberConnection<T> subscriberConnection = iterator.next();

			// Get subscriber connection data
			SubscriberConnectionData subscriberConnectionData = subscriberConnection
					.getSubscriberConnectionData();

			subscriberConnectionDatas.add(subscriberConnectionData);

		}

		// Create subscriber stat
		SubscribeStat subscribeStat = new SubscribeStat(topic,
				subscriberConnectionDatas);

		return subscribeStat;

	}

	protected void stop() {

		// Log
		LOGGER.debug("closing subscriber connections...");

		for (Iterator<SubscriberConnection<T>> iterator = subscriberConnections
				.iterator(); iterator.hasNext();) {

			// Get next subscriber connection
			SubscriberConnection<T> subscriberConnection = iterator.next();

			// Log
			LOGGER.trace("closing subscriber connection: "
					+ subscriberConnection);

			try {

				// Close connection
				subscriberConnection.close();

			} catch (Exception ex) {

				// Log
				LOGGER.error("could not close subscriber connection", ex);

			}

		}

	}

	void updateConnections(GlobalName nodeName, Set<Protocol> protocols) {

		Set<Protocol> connectedProtocols = new HashSet<Protocol>();

		for (Iterator<SubscriberConnection<T>> iterator = subscriberConnections
				.iterator(); iterator.hasNext();) {

			// Get next subscriber connection
			SubscriberConnection<T> subscriberConnection = iterator.next();

			// Get connection protocol
			Protocol protocol = subscriberConnection.getConnectionProtocol();

			if (!protocols.contains(protocol)) {

				// Log
				LOGGER.debug("closing subscriber connection: "
						+ subscriberConnection + " ...");

				try {

					// Close connections
					subscriberConnection.close();

				} catch (IOException ex) {

					// Log
					LOGGER.error("could not close subscriber connection: "
							+ subscriberConnection, ex);

				} finally {

					// Remove from subscribtion connections
					iterator.remove();

				}

			} else {

				// Add to connected protocols
				connectedProtocols.add(protocol);

			}

		}

		for (Iterator<Protocol> iterator = protocols.iterator(); iterator
				.hasNext();) {

			// Get next protocol
			Protocol protocol = iterator.next();

			if (!connectedProtocols.contains(protocol)) {

				try {

					// Create new connection
					newConnection(nodeName, protocol);

				} catch (RosSubscriberException ex) {

					// Log
					LOGGER.error("could not create new subscriber connection",
							ex);

				}

			}

		}

	}

	private void newConnection(GlobalName nodeName, Protocol protocol)
			throws RosSubscriberException {

		if (ProtocolType.TCPROS.equals(protocol.getType())) {

			// Cast to tcp ros protocol
			TcpRosProtocol tcpRosProtocol = (TcpRosProtocol) protocol;

			// Log
			LOGGER.trace("create new tcpros client...");

			// Create tcp ros client
			TcpRosClient<T> tcpRosClient = new TcpRosClient<T>();

			try {

				// Open subscriber connection
				SubscriberConnection<T> subscriberConnection = tcpRosClient
						.open(nodeName, tcpRosProtocol, topic, messageDetails);

				subscriberConnections.add(subscriberConnection);

				// Create subscriber connection thread
				SubscriberConnectionRunnable<T> subscriberConnectionRunnable = new SubscriberConnectionRunnable<T>(
						subscriberConnection, subscriberListener);
				Thread subscriberConnectionThread = new Thread(
						subscriberConnectionRunnable, "subscriber-"
								+ subscriberConnection.getBusInfo()
										.getDestinationId());

				// Start subscriber connection thread
				subscriberConnectionThread.start();

			} catch (TcpRosException ex) {

				// Throw exception
				throw new RosSubscriberException(
						"could not start tcpros client", ex);

			} catch (IOException ex) {

				// Throw exception
				throw new RosSubscriberException(
						"could not start tcpros client", ex);

			}

		} else {

			// Throw exception
			throw new RosSubscriberException("unsupported protocol: "
					+ protocol);

		}

	}

	@Override
	public String toString() {
		return "Subscriber [topic=" + topic + ", type="
				+ messageDetails.getMessageName() + "]";
	}

}
