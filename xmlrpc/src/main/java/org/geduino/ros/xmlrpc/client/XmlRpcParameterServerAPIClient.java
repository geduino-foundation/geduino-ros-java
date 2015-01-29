package org.geduino.ros.xmlrpc.client;

import java.net.URI;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.geduino.ros.core.api.ParameterServerAPI;
import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.core.api.exception.RosApiProtocolException;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToSetAssembler;
import org.geduino.ros.xmlrpc.model.RosResult;

public class XmlRpcParameterServerAPIClient extends XmlRpcClient implements
		ParameterServerAPI {

	@Override
	public void deleteParam(GlobalName callerId, GlobalName key)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("deleteParam",
					new Object[] { callerId.toString(), key.toString() });

			// Get result (just to validate response)
			new RosResult<Integer>(response);

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public Object getParam(GlobalName callerId, GlobalName key)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("getParam",
					new Object[] { callerId.toString(), key.toString() });

			// Get result
			RosResult<Object> result = new RosResult<Object>(response);

			// Get value
			Object value = result.getPayload();

			return value;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public Set<GlobalName> getParamNames(GlobalName callerId)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("getParamNames",
					new Object[] { callerId.toString() });

			// Get result
			RosResult<Object[]> result = new RosResult<Object[]>(response);

			// Get payload
			Object[] payload = result.getPayload();

			// Assemble parameters
			Set<GlobalName> parameters = ObjectToSetAssembler.GLOBAL_NAME
					.assemble(payload);

			return parameters;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public boolean hasParam(GlobalName callerId, GlobalName key)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("hasParam",
					new Object[] { callerId.toString(), key.toString() });

			// Get result
			RosResult<Boolean> result = new RosResult<Boolean>(response);

			// Get has param
			boolean hasParam = result.getPayload().booleanValue();

			return hasParam;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public GlobalName searchParam(GlobalName callerId, String key)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("searchParam",
					new Object[] { callerId.toString(), key });

			// Get result
			RosResult<String> result = new RosResult<String>(response);

			// Get parameter string
			String parameterString = result.getPayload();

			// Assemble parameter
			GlobalName parameter = (GlobalName) Name.parseName(parameterString);

			return parameter;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		} catch (ClassCastException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public void setParam(GlobalName callerId, GlobalName key, Object value)
			throws RosApiException {

		try {

			// Execute request
			Object response = execute("setParam",
					new Object[] { callerId.toString(), key.toString(), value });

			// Get result (just to validate response)
			new RosResult<Integer>(response);

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public Object subscribeParam(GlobalName callerId, URI callerApi,
			GlobalName key) throws RosApiException {

		try {

			// Execute request
			Object response = execute("subscribeParam",
					new Object[] { callerId.toString(), callerApi.toString(),
							key.toString() });

			// Get result
			RosResult<Object> result = new RosResult<Object>(response);

			// Get value
			Object value = result.getPayload();

			return value;

		} catch (XmlRpcException ex) {

			// Throw exception
			throw new RosApiProtocolException(
					"an error occurs at xmlrcp level", ex);

		}

	}

	@Override
	public int unsubscribeParam(GlobalName callerId, URI callerApi,
			GlobalName key) throws RosApiException {

		try {

			// Execute request
			Object response = execute("unsubscribeParam", new Object[] {
					callerId.toString(), callerApi.toString(), key.toString() });

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

}
