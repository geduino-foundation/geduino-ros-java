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
import org.geduino.ros.core.api.SlaveAPI;
import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.BusStats;
import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.node.exception.RosNodeException;
import org.geduino.ros.node.exception.RosPublisherException;
import org.geduino.ros.xmlrpc.client.XmlRpcMasterAPIClient;
import org.geduino.ros.xmlrpc.server.XmlRpcSlaveAPIServer;

public class NodeHandle implements SlaveAPI {

	private static final Logger LOGGER = Logger.getLogger(NodeHandle.class);

	private final Node node;
	private final URI rosMasterUri;

	private final MasterAPI masterAPIClient;
	private final XmlRpcSlaveAPIServer slaveAPIServer;

	private final Map<GlobalName, Publisher<?>> publisherMap;

	public NodeHandle(Node node, URI rosMasterUri) throws RosNodeException {

		this.node = node;
		this.rosMasterUri = rosMasterUri;

		try {

			// Create xml rpc client configuration
			XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
			xmlRpcClientConfigImpl.setServerURL(rosMasterUri.toURL());

			// Create xml rpc client
			XmlRpcMasterAPIClient xmlRpcMasterAPIClient = new XmlRpcMasterAPIClient();
			xmlRpcMasterAPIClient.setConfig(xmlRpcClientConfigImpl);

			masterAPIClient = xmlRpcMasterAPIClient;
			
			// Create slave api server
			slaveAPIServer = new XmlRpcSlaveAPIServer(this);
			slaveAPIServer.getSelectChannelConnector().setPort(node.getNodeUri().getPort());

		} catch (MalformedURLException ex) {

			// Throw exception
			throw new RosNodeException("could not create node", ex);

		}

		publisherMap = new HashMap<GlobalName, Publisher<?>>();

	}

	public void start() throws RosNodeException {

		// Log
		LOGGER.info("starting node: " + node + " ...");

		try {

			// Log
			LOGGER.debug("connection to ros master at: " + rosMasterUri
					+ " ...");

			// Connect to master
			masterAPIClient.getUri(node.getNodeName());
			
			// Log
			LOGGER.debug("starting slave api server...");
			
			// Start slave api server
			slaveAPIServer.start();
			
			// Log
			LOGGER.info("node: " + node + " is started successfully!");

		} catch (RosApiException ex) {

			// Throw exception
			throw new RosNodeException("could not start node", ex);

		} catch (Exception ex) {
			
			// Throw exception
			throw new RosNodeException("could not start node", ex);
			
		}

	}

	public void publish(Publisher<?> publisher) throws RosNodeException {

		// Log
		LOGGER.debug("publishing: " + publisher);

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
			throw new RosNodeException("could not publish: " + publisher, ex);

		}
	}

	public void subscribe(GlobalName topic, SubscriberListener<?> listener) {

	}

	@Override
	public Set<BusInfo> getBusInfo(GlobalName callerId) throws RosApiException {

		// Create bus info set
		Set<BusInfo> busInfoSet = new HashSet<BusInfo>();

		for (Iterator<Publisher<?>> iterator = publisherMap.values().iterator(); iterator
				.hasNext();) {

			// Get next publisher
			Publisher<?> publisher = iterator.next();

			busInfoSet.addAll(publisher.getBusInfo());

		}

		// SUBSCRIBER CONNECTION MUST BE ADDED TOO

		return busInfoSet;

	}

	@Override
	public BusStats getBusStats(GlobalName callerId) throws RosApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getMasterUri(GlobalName callerId) throws RosApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPid(GlobalName callerId) throws RosApiException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<GlobalName, MessageName> getPublications(GlobalName callerId)
			throws RosApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<GlobalName, MessageName> getSubscriptions(GlobalName callerId)
			throws RosApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paramUpdate(GlobalName callerId, GlobalName parameterKey,
			Object parameterValue) throws RosApiException {
		// TODO Auto-generated method stub

	}

	@Override
	public void publisherUpdate(GlobalName callerId, GlobalName topic,
			Set<URI> publishers) throws RosApiException {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
