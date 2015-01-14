package org.geduino.ros.xmlrpc.client;

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
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.xmlrpc.assembler.SystemStateAssembler;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToMapAssembler;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToSetAssembler;
import org.geduino.ros.xmlrpc.model.RosResult;

public class XmlRpcMasterAPIClient extends XmlRpcClient implements MasterAPI {

	@Override
	public void registerService(GlobalName callerId, GlobalName service,
			URI serviceApi, URI callerApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("registerService",
					new Object[] { callerId.toString(), service.toString(),
							serviceApi.toString(), callerApi.toString() });

			// Get result (just to validate response)
			new RosResult<Integer>(response);

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public int unregisterService(GlobalName callerId, GlobalName service,
			URI serviceApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("unregisterService",
					new Object[] { callerId.toString(), service.toString(),
							serviceApi.toString() });

			// Get result
			RosResult<Integer> result = new RosResult<Integer>(response);

			// Get unregistered
			int unregistered = result.getPayload().intValue();

			return unregistered;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public Set<URI> registerSubscriber(GlobalName callerId, GlobalName topic,
			MessageName topicType, URI callerApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("registerSubscriber", new Object[] {
					callerId.toString(), topic.toString(),
					topicType.toString(), callerApi.toString() });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Assemble publishers
			Set<URI> publishers = ObjectToSetAssembler.URI.assemble(payload);

			return publishers;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public int unregisterSubscriber(GlobalName callerId, GlobalName topic,
			URI callerApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("unregisterSubscriber",
					new Object[] { callerId.toString(), topic.toString(),
							callerApi.toString() });

			// Get result
			RosResult<Integer> result = new RosResult<Integer>(response);

			// Get unregistered
			int unregistered = result.getPayload().intValue();

			return unregistered;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public Set<URI> registerPublisher(GlobalName callerId, GlobalName topic,
			MessageName topicType, URI callerApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("registerPublisher", new Object[] {
					callerId.toString(), topic.toString(),
					topicType.toString(), callerApi.toString() });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Assemble subscribers
			Set<URI> subscribers = ObjectToSetAssembler.URI.assemble(payload);

			return subscribers;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public int unregisterPublisher(GlobalName callerId, GlobalName topic,
			URI callerApi) throws RosApiException {

		try {

			// Execute request
			Object response = execute("unregisterPublisher",
					new Object[] { callerId.toString(), topic.toString(),
							callerApi.toString() });

			// Get result
			RosResult<Integer> result = new RosResult<Integer>(response);

			// Get unregistered
			int unregistered = result.getPayload().intValue();

			return unregistered;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public URI lookupNode(GlobalName callerId, GlobalName nodeName)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("lookupNode",
					new Object[] { callerId.toString(), nodeName.toString() });

			// Get result
			RosResult<String> result = new RosResult<String>(response);

			// Get payload
			String payload = result.getPayload();

			// Get uri
			URI uri = new URI(payload);

			return uri;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		} catch (URISyntaxException ex) {

			// Throw exception
			throw new RosApiException("malfomrmed uri", ex);

		}

	}

	@Override
	public Map<GlobalName, MessageName> getPublishedTopics(GlobalName callerId,
			String subgraph) throws RosApiException {

		try {

			// Execute request
			Object response = execute("getPublishedTopics", new Object[] {
					callerId.toString(), subgraph });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Assemble published topic
			Map<GlobalName, MessageName> publishedTopics = ObjectToMapAssembler.K_GLOBAL_NAME_V_MESSAGE_NAME
					.assemble(payload);

			return publishedTopics;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public Map<GlobalName, MessageName> getTopicTypes(GlobalName callerId)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("getTopicTypes",
					new Object[] { callerId.toString() });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Assemble published topic
			Map<GlobalName, MessageName> publishedTopics = ObjectToMapAssembler.K_GLOBAL_NAME_V_MESSAGE_NAME
					.assemble(payload);

			return publishedTopics;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public SystemState getSystemState(GlobalName callerId)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("getSystemState",
					new Object[] { callerId.toString() });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			SystemState systemState = SystemStateAssembler.assemble(payload);

			return systemState;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public URI getUri(GlobalName callerId) throws RosApiException {

		try {

			// Execute request
			Object response = execute("getUri",
					new Object[] { callerId.toString() });

			// Get result
			RosResult<String> result = new RosResult<String>(response);

			// Get payload
			String payload = result.getPayload();

			// Get uri
			URI uri = new URI(payload);

			return uri;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		} catch (URISyntaxException ex) {

			// Throw exception
			throw new RosApiException("malfomrmed uri", ex);

		}

	}

	@Override
	public URL lookupService(GlobalName callerId, GlobalName service)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("lookupService",
					new Object[] { callerId.toString(), service.toString() });

			// Get result
			RosResult<String> result = new RosResult<String>(response);

			// Get payload
			String payload = result.getPayload();

			// Get url
			URL url = new URL(payload);

			return url;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		} catch (MalformedURLException ex) {

			// Throw exception
			throw new RosApiException("malfomrmed url", ex);

		}

	}

}
