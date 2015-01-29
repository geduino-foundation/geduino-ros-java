package org.geduino.ros.node;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.geduino.ros.core.api.MasterAPI;
import org.geduino.ros.core.api.ParameterServerAPI;
import org.geduino.ros.core.api.SlaveAPI;
import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.BusStats;
import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.core.api.model.PublisherStat;
import org.geduino.ros.core.api.model.ServiceStat;
import org.geduino.ros.core.api.model.SubscribeStat;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.node.exception.RosNodeException;
import org.geduino.ros.node.exception.RosPublisherException;
import org.geduino.ros.xmlrpc.client.XmlRpcMasterAPIClient;
import org.geduino.ros.xmlrpc.client.XmlRpcParameterServerAPIClient;
import org.geduino.ros.xmlrpc.client.XmlRpcSlaveAPIClient;
import org.geduino.ros.xmlrpc.server.XmlRpcSlaveAPIServer;

import com.jezhumble.javasysmon.JavaSysMon;

public class NodeHandle implements SlaveAPI {

	private static final Logger LOGGER = Logger.getLogger(NodeHandle.class);

	private static final Set<ProtocolType> SUPPORTED_PROTOCOL_TYPES = new HashSet<ProtocolType>();
	static {
		SUPPORTED_PROTOCOL_TYPES.add(ProtocolType.TCPROS);
	}

	public static final String ROS_DISTRO_PARAM_NAME = "/rosdistro";

	public static final String ROS_VERSION_PARAM_NAME = "/rosversion";

	private final Node node;
	private final URI rosMasterUri;

	private final MasterAPI masterAPIClient;
	private final ParameterServerAPI parameterServerAPIClient;
	private final XmlRpcSlaveAPIServer slaveAPIServer;

	private final Map<GlobalName, Publisher<?>> publisherMap;
	private final Map<GlobalName, Subscriber<?>> subscriberMap;
	private final Set<GlobalName> subscribebParameterNames;

	public NodeHandle(Node node, URI rosMasterUri) throws RosNodeException {

		this.node = node;
		this.rosMasterUri = rosMasterUri;

		try {

			// Create xml rpc client configuration
			XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
			xmlRpcClientConfigImpl.setServerURL(rosMasterUri.toURL());

			// Create master api client
			XmlRpcMasterAPIClient xmlRpcMasterAPIClient = new XmlRpcMasterAPIClient();
			xmlRpcMasterAPIClient.setConfig(xmlRpcClientConfigImpl);

			masterAPIClient = xmlRpcMasterAPIClient;

			// Create parameter server api client
			XmlRpcParameterServerAPIClient xmlRpcParameterServerAPIClient = new XmlRpcParameterServerAPIClient();
			xmlRpcParameterServerAPIClient.setConfig(xmlRpcClientConfigImpl);

			parameterServerAPIClient = xmlRpcParameterServerAPIClient;

			// Create slave api server
			slaveAPIServer = new XmlRpcSlaveAPIServer(this);
			slaveAPIServer.getSelectChannelConnector().setPort(
					node.getNodeUri().getPort());

		} catch (MalformedURLException ex) {

			// Throw exception
			throw new RosNodeException("could not create node", ex);

		}

		publisherMap = new HashMap<GlobalName, Publisher<?>>();
		subscriberMap = new HashMap<GlobalName, Subscriber<?>>();
		subscribebParameterNames = new HashSet<GlobalName>();

	}

	public void start() throws RosNodeException {

		// Log
		LOGGER.info("starting node handle for node: " + node + " ...");

		try {

			// Log
			LOGGER.debug("connection to ros master at: " + rosMasterUri
					+ " ...");

			// Connect to master
			masterAPIClient.getUri(node.getNodeName());

			// Get ros distro and version
			String rosDistro = (String) getParam(ROS_DISTRO_PARAM_NAME, "N/A");
			String rosVersion = (String) getParam(ROS_VERSION_PARAM_NAME, "N/A");

			// Log
			LOGGER.debug("master is running ros distro: " + rosDistro.trim()
					+ ", version: " + rosVersion.trim());

			// Log
			LOGGER.debug("starting slave api server...");

			// Start slave api server
			slaveAPIServer.start();

			// Log
			LOGGER.info("node handle for node: " + node
					+ " is started successfully!");

		} catch (RosApiException ex) {

			// Throw exception
			throw new RosNodeException("could not start node", ex);

		} catch (Exception ex) {

			// Throw exception
			throw new RosNodeException("could not start node", ex);

		}

	}

	public void stop() throws RosNodeException {

		// Log
		LOGGER.info("stopping node handle for node: " + node + " ...");

		// Log
		LOGGER.debug("stopping slave api server...");

		try {

			// Stop slave api server
			slaveAPIServer.stop();

		} catch (Exception ex) {

			// Throw exception
			throw new RosNodeException("could not stop node", ex);

		}

		// Log
		LOGGER.debug("unregistering publishers...");

		for (Iterator<Publisher<?>> iterator = publisherMap.values().iterator(); iterator
				.hasNext();) {

			// Get next publisher
			Publisher<?> publisher = iterator.next();

			// Log
			LOGGER.trace("unregistering publisher for topic: "
					+ publisher.getTopic().toString() + " ...");

			try {

				// Unregister publisher
				masterAPIClient.unregisterPublisher(node.getNodeName(),
						publisher.getTopic(), node.getNodeUri());

			} catch (RosApiException ex) {

				// Throw exception
				throw new RosNodeException("could not unregister publisher", ex);

			}

			// Stop publisher
			publisher.stop();

		}

		// Log
		LOGGER.debug("unregistering subscribers...");

		for (Iterator<Subscriber<?>> iterator = subscriberMap.values()
				.iterator(); iterator.hasNext();) {

			// Get next subscriber
			Subscriber<?> subscriber = iterator.next();

			// Log
			LOGGER.trace("unregistering subscriber for topic: "
					+ subscriber.getTopic().toString() + " ...");

			try {

				// Unregister subscriber
				masterAPIClient.unregisterSubscriber(node.getNodeName(),
						subscriber.getTopic(), node.getNodeUri());

			} catch (RosApiException ex) {

				// Throw exception
				throw new RosNodeException("could unregister subscriber", ex);

			}

			// Stop subscriber
			subscriber.stop();

		}

		// Log
		LOGGER.debug("unsubscribing params...");

		for (Iterator<GlobalName> iterator = subscribebParameterNames
				.iterator(); iterator.hasNext();) {

			// Get next parameter name
			GlobalName parameterName = iterator.next();

			// Log
			LOGGER.trace("unsubscribing parameter: " + parameterName.toString()
					+ " ...");

			try {

				// Unsubscribing parameter
				parameterServerAPIClient.unsubscribeParam(node.getNodeName(),
						node.getNodeUri(), parameterName);

			} catch (RosApiException ex) {

				// Throw exception
				throw new RosNodeException("could unregister param", ex);

			}

		}

		// Log
		LOGGER.debug("node handle stopped");

	}

	public void registerPublisher(Publisher<?> publisher)
			throws RosNodeException {

		// Log
		LOGGER.debug("registering publisher: " + publisher);

		try {

			// Register publisher
			masterAPIClient.registerPublisher(node.getNodeName(), publisher
					.getTopic(),
					publisher.getMessageDetails().getMessageName(), node
							.getNodeUri());

			// Put on publisher map
			publisherMap.put(publisher.getTopic(), publisher);

		} catch (RosApiException ex) {

			// Throw exception
			throw new RosNodeException("could not register publisher: "
					+ publisher, ex);

		}

	}

	public void registerSubscriber(Subscriber<?> subscriber)
			throws RosNodeException {

		// Log
		LOGGER.debug("registering subscriber: " + subscriber);

		try {

			// Register subscriber
			Set<URI> publishers = masterAPIClient.registerSubscriber(node
					.getNodeName(), subscriber.getTopic(), subscriber
					.getMessageDetails().getMessageName(), node.getNodeUri());

			// Put on subscriber map
			subscriberMap.put(subscriber.getTopic(), subscriber);

			// Log
			LOGGER.debug("topic: " + subscriber.getTopic() + " publishers: "
					+ publishers + " ...");

			// Handle publishers
			handlePublishers(subscriber.getTopic(), publishers);

		} catch (RosApiException ex) {

			// Throw exception
			throw new RosNodeException("could not register subscriber: "
					+ subscriber, ex);

		}

	}

	public Object getParam(String key, Object defaultValue)
			throws RosApiException {

		// Get parameter resolved name
		GlobalName parameterName = node.getResolvedName(key);

		if (parameterServerAPIClient
				.hasParam(node.getNodeName(), parameterName)) {

			// Get param value
			Object value = parameterServerAPIClient.getParam(
					node.getNodeName(), parameterName);

			if (defaultValue.getClass().isAssignableFrom(value.getClass())) {

				return value;

			} else {

				// Log
				LOGGER.warn("parameter: " + parameterName
						+ " expected value class is: "
						+ defaultValue.getClass() + " but was: "
						+ value.getClass());

				return defaultValue;

			}

		} else {

			// Set param value
			parameterServerAPIClient.setParam(node.getNodeName(),
					parameterName, defaultValue);

			// Return default value
			return defaultValue;

		}

	}

	public void setParam(String key, Object value) throws RosApiException {

		// Get parameter resolved name
		GlobalName parameterName = node.getResolvedName(key);

		// Set param
		parameterServerAPIClient.setParam(node.getNodeName(), parameterName,
				value);

	}

	public void subscribeParam(String key) throws RosApiException {

		// Get parameter resolved name
		GlobalName parameterName = node.getResolvedName(key);

		// Log
		LOGGER.trace("subscribing param: " + parameterName.toString() + " ...");

		// Subscribe param
		parameterServerAPIClient.subscribeParam(node.getNodeName(),
				node.getNodeUri(), parameterName);

		// Add to subscribed parameter names
		subscribebParameterNames.add(parameterName);

	}

	@Override
	public Set<BusInfo> getBusInfo(GlobalName callerId) throws RosApiException {

		// Create bus info set
		Set<BusInfo> busInfos = new HashSet<BusInfo>();

		for (Iterator<Publisher<?>> iterator = publisherMap.values().iterator(); iterator
				.hasNext();) {

			// Get next publisher
			Publisher<?> publisher = iterator.next();

			busInfos.addAll(publisher.getBusInfos());

		}

		for (Iterator<Subscriber<?>> iterator = subscriberMap.values()
				.iterator(); iterator.hasNext();) {

			// Get next subscriber
			Subscriber<?> subscriber = iterator.next();

			busInfos.addAll(subscriber.getBusInfos());

		}

		return busInfos;

	}

	@Override
	public BusStats getBusStats(GlobalName callerId) throws RosApiException {

		// Create publisher stat set
		Set<PublisherStat> publisherStats = new HashSet<PublisherStat>();

		for (Iterator<Publisher<?>> iterator = publisherMap.values().iterator(); iterator
				.hasNext();) {

			// Get next publisher
			Publisher<?> publisher = iterator.next();

			publisherStats.add(publisher.getPublisherStat());

		}

		// Create subscriber stat set
		Set<SubscribeStat> subscribeStats = new HashSet<SubscribeStat>();

		for (Iterator<Subscriber<?>> iterator = subscriberMap.values()
				.iterator(); iterator.hasNext();) {

			// Get next subscriber
			Subscriber<?> subscriber = iterator.next();

			subscribeStats.add(subscriber.getSubscriberStat());

		}

		// Create service stat
		ServiceStat serviceStat = new ServiceStat(0, 0, 0);
		// SERVICECONNECTION MUST BE ADDED TOO

		// Create bus stats
		BusStats busStats = new BusStats(publisherStats, subscribeStats,
				serviceStat);

		return busStats;

	}

	@Override
	public URI getMasterUri(GlobalName callerId) throws RosApiException {
		return rosMasterUri;
	}

	@Override
	public int getPid(GlobalName callerId) throws RosApiException {

		// Create java sys mon
		JavaSysMon javaSysMon = new JavaSysMon();

		// Get process id
		int pid = javaSysMon.currentPid();

		return pid;

	}

	@Override
	public Map<GlobalName, MessageName> getPublications(GlobalName callerId)
			throws RosApiException {

		Map<GlobalName, MessageName> publications = new HashMap<GlobalName, MessageName>();

		for (Iterator<Publisher<?>> iterator = publisherMap.values().iterator(); iterator
				.hasNext();) {

			// Get next publisher
			Publisher<?> publisher = iterator.next();

			// Get topic and type
			GlobalName topic = publisher.getTopic();
			MessageName type = publisher.getMessageDetails().getMessageName();

			publications.put(topic, type);

		}

		return publications;

	}

	@Override
	public Map<GlobalName, MessageName> getSubscriptions(GlobalName callerId)
			throws RosApiException {

		Map<GlobalName, MessageName> subscrptions = new HashMap<GlobalName, MessageName>();

		for (Iterator<Subscriber<?>> iterator = subscriberMap.values()
				.iterator(); iterator.hasNext();) {

			// Get next subscriber
			Subscriber<?> subscriber = iterator.next();

			// Get topic and type
			GlobalName topic = subscriber.getTopic();
			MessageName type = subscriber.getMessageDetails().getMessageName();

			subscrptions.put(topic, type);

		}

		return subscrptions;

	}

	@Override
	public void paramUpdate(GlobalName callerId, GlobalName parameterKey,
			Object parameterValue) throws RosApiException {

		// Log
		LOGGER.trace("parameter: " + parameterKey.toString() + " updated by: "
				+ callerId);

		// Invoke handke method on node
		node.paramUpdated(parameterKey, parameterValue);

	}

	@Override
	public void publisherUpdate(GlobalName callerId, GlobalName topic,
			Set<URI> publishers) throws RosApiException {

		// Handle publishers
		handlePublishers(topic, publishers);

	}

	@Override
	public Protocol requestTopic(GlobalName callerId, GlobalName topic,
			Set<ProtocolType> protocolTypes) throws RosApiException {

		// Log
		LOGGER.trace("looking for publisher of topic: " + topic + " ...");

		// Get publisher for given topic
		Publisher<?> publisher = publisherMap.get(topic);

		if (publisher != null) {

			try {

				// Get new connection from publisher
				Protocol protocol = publisher.newConnection(node.getNodeName(),
						node.getNodeUri(), protocolTypes);

				return protocol;

			} catch (RosPublisherException ex) {

				// Throw exceptoon
				throw new RosApiException("could now handle new connection", ex);

			}

		} else {

			// Throw exceptoon
			throw new RosApiException("cannot find publisher for topic: "
					+ topic);

		}

	}

	@Override
	public void shutdown(GlobalName callerId, String message)
			throws RosApiException {
		
		// Log
		LOGGER.debug("shutdown request from: " + callerId.toString() + " with message: " + message);
		
		// Invoke shutdown method on node
		node.shutdown();

	}

	private SlaveAPI createSlaveApiClient(URI nodeUri)
			throws MalformedURLException {

		// Create xml rpc client configuration
		XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
		xmlRpcClientConfigImpl.setServerURL(nodeUri.toURL());

		// Create xml rpc slave api client
		XmlRpcSlaveAPIClient xmlRpcSlaveAPIClient = new XmlRpcSlaveAPIClient();
		xmlRpcSlaveAPIClient.setConfig(xmlRpcClientConfigImpl);

		return xmlRpcSlaveAPIClient;

	}

	private void handlePublishers(GlobalName topic, Set<URI> publishers)
			throws RosApiException {

		// Log
		LOGGER.trace("looking for subscriber of topic: " + topic + " ...");

		// Create protocol set
		Set<Protocol> protocols = new HashSet<Protocol>();

		for (Iterator<URI> iterator = publishers.iterator(); iterator.hasNext();) {

			// Get next publisher
			URI publisher = iterator.next();

			// Log
			LOGGER.trace("request topic to: " + publisher);

			try {

				// Create slave api client
				SlaveAPI slaveAPIClient = createSlaveApiClient(publisher);

				// Request topic
				Protocol protocol = slaveAPIClient.requestTopic(
						node.getNodeName(), topic, SUPPORTED_PROTOCOL_TYPES);

				protocols.add(protocol);

			} catch (MalformedURLException ex) {

				// Log
				LOGGER.error("could not get transport protocol", ex);

			} catch (RosApiException ex) {

				// Log
				LOGGER.error("could not get transport protocol", ex);

			}

		}

		// Get subscriber for given topic
		Subscriber<?> subscriber = subscriberMap.get(topic);

		if (subscriber != null) {

			// Update connections
			subscriber.updateConnections(node.getNodeName(), protocols);

		} else {

			// Throw exceptoon
			throw new RosApiException("cannot find subscriber for topic: "
					+ topic);

		}

	}

}
