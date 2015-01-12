package org.geduino.ros.core.api;


import java.net.URI;
import java.util.Set;

import org.geduino.ros.core.api.exception.RosApiException;

public interface ParameterServerAPI {

	/**
	 * Delete parameter
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param key
	 *            Parameter name
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	void deleteParam(String callerId, String key) throws RosApiException;

	/**
	 * Set parameter. Furthermore, it will replace all existing parameters in
	 * the key parameter namespace with the parameters in value. You must set
	 * parameters individually if you wish to perform a union update.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param key
	 *            Parameter name
	 * @param value
	 *            Parameter value.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	void setParam(String callerId, String key, Object value)
			throws RosApiException;

	/**
	 * Retrieve parameter value from server.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param key
	 *            Parameter name
	 * @return The parameter value
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Object getParam(String callerId, String key) throws RosApiException;

	/**
	 * Search for parameter key on the Parameter Server. Search starts in
	 * caller's namespace and proceeds upwards through parent namespaces until
	 * Parameter Server finds a matching key. searchParam()'s behavior is to
	 * search for the first partial match. For example, imagine that there are
	 * two 'robot_description' parameters <br>
	 * <code>/robot_description<br>
	 * /robot_description/arm<br>
	 * /robot_description/base<br>
	 * /pr2/robot_description<br>
	 * /pr2/robot_description/base
	 * </code><br>
	 * If I start in the namespace /pr2/foo and search for robot_description,
	 * searchParam() will match /pr2/robot_description. If I search for
	 * robot_description/arm it will return /pr2/robot_description/arm, even
	 * though that parameter does not exist (yet).
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param key
	 *            Parameter name to search for.
	 * @return a set of found keys
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Set<String> searchParam(String callerId, String key) throws RosApiException;

	/**
	 * Retrieve parameter value from server and subscribe to updates to that
	 * param. See paramUpdate() in the Slave API.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param callerApi
	 *            Node API URI of subscriber for paramUpdate callbacks.
	 * @param key
	 *            Parameter name
	 * @return The parameter value
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Object subscribeParam(String callerId, URI callerApi, String key)
			throws RosApiException;

	/**
	 * Unsubscribe updates to given param
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param callerApi
	 *            Node API URI of subscriber for paramUpdate callbacks.
	 * @param key
	 *            Parameter name
	 * @return The number of unsubscribes. If numUnsubscribed is zero it means
	 *         that the caller was not subscribed to the parameter.
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	int unsubscribeParam(String callerId, URI callerApi, String key)
			throws RosApiException;

	/**
	 * Check if parameter is stored on server.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @param key
	 *            Parameter name
	 * @return true if parameter is stored on server, false otherwise
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	boolean hasParam(String callerId, String key) throws RosApiException;

	/**
	 * Get list of all parameter names stored on this server.
	 * 
	 * @param callerId
	 *            ROS caller ID
	 * @return a set of parameter names
	 * @throws RosApiException
	 *             if an error occurs with api
	 */
	Set<String> getParamNames(String callerId) throws RosApiException;
	
}
