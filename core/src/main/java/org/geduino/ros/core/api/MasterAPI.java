package org.geduino.ros.core.api;


import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.core.api.model.SystemState;

public interface MasterAPI {

	/**
	 * Register the caller as a provider of the specified service.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param service
	 *            Fully-qualified name of service
	 * @param serviceApi
	 *            ROSRPC Service URI
	 * @param callerApi
	 *            URI of caller node
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	void registerService(String callerId, String service, URI serviceApi,
			URI callerApi) throws RosApiException;

	/**
	 * Unregister the caller as a provider of the specified service.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param service
	 *            Fully-qualified name of service
	 * @param serviceApi
	 *            API URI of service to unregister. Unregistration will only
	 *            occur if current registration matches.
	 * @return Number of unregistrations (either 0 or 1). If this is zero it
	 *         means that the caller was not registered as a service provider.
	 *         The call still succeeds as the intended final state is reached.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	int unregisterService(String callerId, String service, URI serviceApi)
			throws RosApiException;

	/**
	 * Subscribe the caller to the specified topic. In addition to receiving a
	 * list of current publishers, the subscriber will also receive
	 * notifications of new publishers via the publisherUpdate API.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param topic
	 *            Fully-qualified name of topic
	 * @param topicType
	 *            Datatype for topic. Must be a package-resource name, i.e. the
	 *            .msg name.
	 * @param callerApi
	 *            API URI of subscriber to register. Will be used for new
	 *            publisher notifications.
	 * @return A list of URIs for nodes currently publishing the specified
	 *         topic.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Set<URI> registerSubscriber(String callerId, String topic,
			String topicType, URI callerApi) throws RosApiException;

	/**
	 * Unregister the caller as a subscriber of the topic.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param topic
	 *            Fully-qualified name of topic
	 * @param callerApi
	 *            API URI of service to unregister. Unregistration will only
	 *            occur if current registration matches.
	 * @return Number of unregistrations. If number of unregistrations is zero
	 *         it means that the caller was not registered as a subscriber. The
	 *         call still succeeds as the intended final state is reached.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	int unregisterSubscriber(String callerId, String topic, URI callerApi)
			throws RosApiException;

	/**
	 * Register the caller as a publisher the topic.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param topic
	 *            Fully-qualified name of topic
	 * @param topicType
	 *            Datatype for topic. Must be a package-resource name, i.e. the
	 *            .msg name.
	 * @param callerApi
	 *            API URI of publisher to register.
	 * @return List of current subscribers of topic in the form of URIs.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Set<URI> registerPublisher(String callerId, String topic,
			String topicType, URI callerApi) throws RosApiException;

	/**
	 * Unregister the caller as a publisher of the topic.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param topic
	 *            Fully-qualified name of topic
	 * @param callerApi
	 *            API URI of publisher to unregister. Unregistration will only
	 *            occur if current registration matches.
	 * @return Number of unregistrations. If number of unregistrations is zero
	 *         it means that the caller was not registered as a publisher. The
	 *         call still succeeds as the intended final state is reached.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	int unregisterPublisher(String callerId, String topic, URI callerApi)
			throws RosApiException;

	/**
	 * Get the URI of the node with the associated name/caller_id. This API is
	 * for looking information about publishers and subscribers. Use
	 * lookupService instead to lookup URIs.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param nodeName
	 *            Name of node to lookup
	 * @return The XML-RPC URI of the node
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	URI lookupNode(String callerId, String nodeName) throws RosApiException;

	/**
	 * Get list of topics that can be subscribed to. This does not return topics
	 * that have no publishers. See getSystemState() to get more comprehensive
	 * list.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param subgraph
	 *            Restrict topic names to match within the specified subgraph.
	 *            Subgraph namespace is resolved relative to the caller's
	 *            namespace. Use emptry string to specify all names.
	 * @return A map of topic name related to its topic type
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Map<String, String> getPublishedTopics(String callerId, String subgraph)
			throws RosApiException;

	/**
	 * Retrieve list topic names and their types.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @return A map of topic name related to its topic type
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Map<String, String> getTopicTypes(String callerId) throws RosApiException;

	/**
	 * Retrieve list representation of system state (i.e. publishers,
	 * subscribers, and services).
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @return The system state
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	SystemState getSystemState(String callerId) throws RosApiException;

	/**
	 * Get the URI of the master.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @return The URI of the master
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	URI getUri(String callerId) throws RosApiException;

	/**
	 * Lookup all provider of a particular service.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param service
	 *            Fully-qualified name of service
	 * @return Service URL is provides address and port of the service. Fails if
	 *         there is no provider.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	URL lookupService(String callerId, String service) throws RosApiException;

}
