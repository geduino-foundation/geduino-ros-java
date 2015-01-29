package org.geduino.ros.xmlrpc.client;

import java.net.URI;
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
import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToMapAssembler;
import org.geduino.ros.xmlrpc.assembler.util.SetToObjectAssembler;
import org.geduino.ros.xmlrpc.assembler.util.impl.ObjectToProtocolEntryAssembler;
import org.geduino.ros.xmlrpc.exception.SlaveAPINotAllowedException;
import org.geduino.ros.xmlrpc.model.RosResult;

public class XmlRpcSlaveAPIClient extends XmlRpcClient implements SlaveAPI {

	@Override
	public BusStats getBusStats(GlobalName callerId) throws RosApiException {
		throw new SlaveAPINotAllowedException();
	}

	@Override
	public Set<BusInfo> getBusInfo(GlobalName callerId) throws RosApiException {
		throw new SlaveAPINotAllowedException();
	}

	@Override
	public URI getMasterUri(GlobalName callerId) throws RosApiException {
		throw new SlaveAPINotAllowedException();
	}

	@Override
	public void shutdown(GlobalName callerId, String message)
			throws RosApiException {
		throw new SlaveAPINotAllowedException();
	}

	@Override
	public int getPid(GlobalName callerId) throws RosApiException {
		throw new SlaveAPINotAllowedException();
	}

	@Override
	public Map<GlobalName, MessageName> getSubscriptions(GlobalName callerId)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("getSubscriptions",
					new Object[] { callerId });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Get published topic
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
	public Map<GlobalName, MessageName> getPublications(GlobalName callerId)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("getPublications",
					new Object[] { callerId });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Get published topic
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
	public void paramUpdate(GlobalName callerId, GlobalName parameterKey,
			Object parameterValue) throws RosApiException {
		throw new SlaveAPINotAllowedException();
	}

	@Override
	public void publisherUpdate(GlobalName callerId, GlobalName topic,
			Set<URI> publishers) throws RosApiException {
		throw new SlaveAPINotAllowedException();
	}

	@Override
	public Protocol requestTopic(GlobalName callerId, GlobalName topic,
			Set<ProtocolType> protocolTypes) throws RosApiException {

		try {

			// Assemble protocol type
			Object[] protocolTypeObjects = SetToObjectAssembler.PROTOCOL_TYPE
					.assemble(protocolTypes);

			// Execute request
			Object response = execute("requestTopic",
					new Object[] { callerId.toString(), topic.toString(),
							protocolTypeObjects });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Assemble protocol
			Protocol protocol = ObjectToProtocolEntryAssembler.INSTANCE
					.assemble(payload);

			return protocol;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

}
