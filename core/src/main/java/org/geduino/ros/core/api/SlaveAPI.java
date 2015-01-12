package org.geduino.ros.core.api;


import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.BusStats;
import org.geduino.ros.core.api.model.Protocol;

public interface SlaveAPI {

	/**
	 * Retrieve transport/topic statistics.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @return Transport/topic statistics.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	BusStats getBusStats(String callerId) throws RosApiException;

	/**
	 * Retrieve transport/topic connection information.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @return Transport/topic connection information.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Set<BusInfo> getBusInfo(String callerId) throws RosApiException;

	/**
	 * Get the URI of the master node.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @return URI of the master node.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	URI getMasterUri(String callerId) throws RosApiException;

	/**
	 * Stop this server.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @param message
	 *            A message describing why the node is being shutdown.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	void shutdown(String callerId, String message) throws RosApiException;

	/**
	 * Get the PID of this server.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @return The server process id
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	int getPid(String callerId) throws RosApiException;

	/**
	 * Retrieve a list of topics that this node subscribes to.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @return subscribed topic as topic name and type pair
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Map<String, String> getSubscriptions(String callerId)
			throws RosApiException;

	/**
	 * Retrieve a list of topics that this node publishes.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @return published topic as topic name and type pair
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Map<String, String> getPublications(String callerId) throws RosApiException;

	/**
	 * Callback from master with updated value of subscribed parameter.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @param parameterKey
	 *            Parameter name, globally resolved.
	 * @param parameterValue
	 *            New parameter value.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	void paramUpdate(String callerId, String parameterKey, Object parameterValue)
			throws RosApiException;

	/**
	 * Callback from master of current publisher list for specified topic.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @param topic
	 *            Topic name.
	 * @param publishers
	 *            List of current publishers for topic in the form of URIs
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	void publisherUpdate(String callerId, String topic, Set<URI> publishers)
			throws RosApiException;

	/**
	 * Publisher node API method called by a subscriber node. This requests that
	 * source allocate a channel for communication. Subscriber provides a list
	 * of desired protocols for communication. Publisher returns the selected
	 * protocol along with any additional params required for establishing
	 * connection. For example, for a TCP/IP-based connection, the source node
	 * may return a port number of TCP/IP server.
	 * 
	 * @param callerId
	 *            ROS caller ID.
	 * @param topic
	 *            Topic name.
	 * @param protocols
	 *            List of desired protocols for communication in order of
	 *            preference.
	 * @return Selected protocol
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Protocol requestTopic(String callerId, String topic,
			Set<Protocol> protocols) throws RosApiException;

}
