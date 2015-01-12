package org.geduino.ros.xmlrpc;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.geduino.ros.core.api.MasterAPI;
import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.core.api.exception.RosApiProtocolException;
import org.geduino.ros.core.api.model.SystemState;
import org.geduino.ros.xmlrpc.util.MapUtil;
import org.geduino.ros.xmlrpc.util.SetUtil;

public class XmlRpcMasterAPIClient extends XmlRpcClient implements MasterAPI {

	@Override
	public void registerService(String callerId, String service,
			URI serviceApi, URI callerApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("registerService",
					new Object[] { callerId, service, serviceApi.toString(),
							callerApi.toString() });

			// Get result (just to validate response)
			new RosResult<Integer>(response);

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public int unregisterService(String callerId, String service, URI serviceApi)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("unregisterService", new Object[] {
					callerId, service, serviceApi.toString() });

			// Get result
			RosResult<Integer> result = new RosResult<Integer>(response);

			// Get unregistered
			int unregistered = result.getPayload().intValue();

			return unregistered;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public Set<URI> registerSubscriber(String callerId, String topic,
			String topicType, URI callerApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("registerSubscriber", new Object[] {
					callerId, topic, topicType, callerApi.toString() });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Get publishers
			Set<URI> publishers = SetUtil.E_URI.toSet(payload);

			return publishers;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public int unregisterSubscriber(String callerId, String topic, URI callerApi)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("unregisterSubscriber", new Object[] {
					callerId, topic, callerApi.toString() });

			// Get result
			RosResult<Integer> result = new RosResult<Integer>(response);

			// Get unregistered
			int unregistered = result.getPayload().intValue();

			return unregistered;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public Set<URI> registerPublisher(String callerId, String topic,
			String topicType, URI callerApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("registerPublisher", new Object[] {
					callerId, topic, topicType, callerApi.toString() });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Get subscribers
			Set<URI> subscribers = SetUtil.E_URI.toSet(payload);

			return subscribers;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public int unregisterPublisher(String callerId, String topic, URI callerApi)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("unregisterPublisher", new Object[] {
					callerId, topic, callerApi.toString() });

			// Get result
			RosResult<Integer> result = new RosResult<Integer>(response);

			// Get unregistered
			int unregistered = result.getPayload().intValue();

			return unregistered;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public URI lookupNode(String callerId, String nodeName)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("lookupNode", new Object[] { callerId,
					nodeName });

			// Get result
			RosResult<String> result = new RosResult<String>(response);

			// Get payload
			String payload = result.getPayload();

			// Get uri
			URI uri = new URI(payload);

			return uri;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		} catch (URISyntaxException ex) {

			// Throw exception
			throw new RosApiException("malfomrmed uri", ex);

		}

	}

	@Override
	public Map<String, String> getPublishedTopics(String callerId,
			String subgraph) throws RosApiException {

		try {

			// Execute request
			Object response = execute("getPublishedTopics", new Object[] {
					callerId, subgraph });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Get published topic
			Map<String, String> publishedTopics = MapUtil.K_STRING_V_STRING
					.toMap(payload);

			return publishedTopics;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public Map<String, String> getTopicTypes(String callerId)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("getTopicTypes",
					new Object[] { callerId });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Get published topic
			Map<String, String> publishedTopics = MapUtil.K_STRING_V_STRING
					.toMap(payload);

			return publishedTopics;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public SystemState getSystemState(String callerId) throws RosApiException {

		try {

			// Execute request
			Object response = execute("getSystemState",
					new Object[] { callerId });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			if (payload.length == 3) {

				// Get publishers, subscribers and services
				Map<String, Set<String>> publishers = MapUtil.K_STRING_V_SET_E_STRING
						.toMap((Object[]) payload[0]);
				Map<String, Set<String>> subscribers = MapUtil.K_STRING_V_SET_E_STRING
						.toMap((Object[]) payload[1]);
				Map<String, Set<String>> services = MapUtil.K_STRING_V_SET_E_STRING
						.toMap((Object[]) payload[2]);

				// Create system state
				SystemState systemState = new SystemState(publishers,
						subscribers, services);

				return systemState;

			} else {

				// Throw exception
				throw new RosApiException("unexpected payload length: "
						+ payload.length);

			}

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

	@Override
	public URI getUri(String callerId) throws RosApiException {

		try {

			// Execute request
			Object response = execute("getUri", new Object[] { callerId });

			// Get result
			RosResult<String> result = new RosResult<String>(response);

			// Get payload
			String payload = result.getPayload();

			// Get uri
			URI uri = new URI(payload);

			return uri;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		} catch (URISyntaxException ex) {

			// Throw exception
			throw new RosApiException("malfomrmed uri", ex);

		}

	}

	@Override
	public URL lookupService(String callerId, String service)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("lookupService", new Object[] { callerId,
					service });

			// Get result
			RosResult<String> result = new RosResult<String>(response);

			// Get payload
			String payload = result.getPayload();

			// Get url
			URL url = new URL(payload);

			return url;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		} catch (MalformedURLException ex) {

			// Throw exception
			throw new RosApiException("malfomrmed url", ex);

		}

	}

}
