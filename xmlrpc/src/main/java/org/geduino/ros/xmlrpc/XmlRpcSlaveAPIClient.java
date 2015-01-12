package org.geduino.ros.xmlrpc;


import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.geduino.ros.core.api.SlaveAPI;
import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.core.api.exception.RosApiProtocolException;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.BusStats;
import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.core.exception.NotYetImplementedException;
import org.geduino.ros.xmlrpc.util.MapUtil;

public class XmlRpcSlaveAPIClient extends XmlRpcClient implements SlaveAPI {

	@Override
	public BusStats getBusStats(String callerId) throws RosApiException {
		throw new NotYetImplementedException();
	}

	@Override
	public Set<BusInfo> getBusInfo(String callerId) throws RosApiException {
		throw new NotYetImplementedException();
	}

	@Override
	public URI getMasterUri(String callerId) throws RosApiException {
		throw new NotYetImplementedException();
	}

	@Override
	public void shutdown(String callerId, String message)
			throws RosApiException {
		throw new NotYetImplementedException();
	}

	@Override
	public int getPid(String callerId) throws RosApiException {
		throw new NotYetImplementedException();
	}

	@Override
	public Map<String, String> getSubscriptions(String callerId)
			throws RosApiException {
		throw new NotYetImplementedException();
	}

	@Override
	public Map<String, String> getPublications(String callerId)
			throws RosApiException {
		
		try {

			// Execute request
			Object response = execute("getPublications", new Object[] {
					callerId});

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
	public void paramUpdate(String callerId, String parameterKey,
			Object parameterValue) throws RosApiException {
		throw new NotYetImplementedException();
	}

	@Override
	public void publisherUpdate(String callerId, String topic,
			Set<URI> publishers) throws RosApiException {
		throw new NotYetImplementedException();
	}

	@Override
	public Protocol requestTopic(String callerId, String topic,
			Set<Protocol> protocols) throws RosApiException {

		try {

			// Create protocol
			Object[] protocolObjects = new Object[protocols.size()];

			int index = 0;

			for (Iterator<Protocol> iterator = protocols.iterator(); iterator
					.hasNext();) {

				// Get next protocol
				Protocol protocol = iterator.next();

				// Get protocol name and arguments
				String name = protocol.getName();
				Object[] arguments = protocol.getArguments();

				// Create protocol object
				Object[] protocolObject = new Object[arguments.length + 1];
				protocolObject[0] = name;

				for (int argumentIndex = 0; argumentIndex < arguments.length; argumentIndex++) {
					protocolObject[argumentIndex + 1] = arguments[argumentIndex];
				}

				protocolObjects[index] = protocolObject;

			}

			// Execute request
			Object response = execute("requestTopic", new Object[] { callerId,
					topic, protocolObjects });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Get protocol name
			String name = (String) payload[0];

			// Get protocol arguments
			Object[] arguments = Arrays.copyOfRange(payload, 1, payload.length);

			// Create protocol
			Protocol protocol = new Protocol(name, arguments);

			return protocol;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException("an error occurs at xmlrcp level",
					ex);

		}

	}

}
