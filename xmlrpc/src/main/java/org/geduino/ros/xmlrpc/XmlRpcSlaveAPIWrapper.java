package org.geduino.ros.xmlrpc;


import java.net.URI;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.geduino.ros.core.api.SlaveAPI;
import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.core.api.model.BusInfo;
import org.geduino.ros.core.api.model.BusStats;
import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.xmlrpc.util.BusInfoObjectsAssembler;
import org.geduino.ros.xmlrpc.util.BusStatsObjectsAssembler;
import org.geduino.ros.xmlrpc.util.MapUtil;
import org.geduino.ros.xmlrpc.util.SetUtil;

public class XmlRpcSlaveAPIWrapper {

	private static final Logger LOGGER = Logger
			.getLogger(XmlRpcSlaveAPIWrapper.class);

	private final SlaveAPI slaveAPI;

	public XmlRpcSlaveAPIWrapper(SlaveAPI slaveAPI) {
		this.slaveAPI = slaveAPI;
	}

	public Object[] getBusStats(String callerId) {

		// Log
		LOGGER.trace("getBusStats request from callerId: " + callerId + " ...");

		try {

			// Get bus stats
			BusStats busStats = slaveAPI.getBusStats(callerId);

			// Assemble bus stats objects
			Object[] busStatsObject = BusStatsObjectsAssembler
					.assemble(busStats);

			// Create result
			Object[] result = createSuccessResult("bus stats", busStatsObject);

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] getBusInfo(String callerId) {

		// Log
		LOGGER.trace("getBusInfo request from callerId: " + callerId + " ...");

		try {

			// Get bus info
			Set<BusInfo> busInfos = slaveAPI.getBusInfo(callerId);

			// Assemble bus info objects
			Object[] busStatsObject = BusInfoObjectsAssembler
					.assemble(busInfos);

			// Create result
			Object[] result = createSuccessResult("bus info", busStatsObject);

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] getMasterUri(String callerId) {

		// Log
		LOGGER.trace("getMasterUri request from callerId: " + callerId + " ...");

		try {

			// Get master uri
			URI masterUri = slaveAPI.getMasterUri(callerId);

			// Create result
			Object[] result = createSuccessResult("master uri",
					masterUri.toString());

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] shutdown(String callerId, String message) {

		// Log
		LOGGER.trace("shutdown request from callerId: " + callerId + " ...");

		try {

			// Shutdown
			slaveAPI.shutdown(callerId, message);

			// Create result
			Object[] result = createIgnoreResult("shutting down...");

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] getPid(String callerId) {

		// Log
		LOGGER.trace("getPid request from callerId: " + callerId + " ...");

		try {

			// Get pid
			int pid = slaveAPI.getPid(callerId);

			// Create result
			Object[] result = createSuccessResult("pid", pid);

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] getSubscriptions(String callerId) {

		// Log
		LOGGER.trace("getSubscriptions request from callerId: " + callerId
				+ " ...");

		try {

			// Get subscriptions
			Map<String, String> subscriptions = slaveAPI
					.getSubscriptions(callerId);

			// Get subscripbtion objects
			Object[] subscriptionObjects = MapUtil.K_STRING_V_STRING
					.toObjects(subscriptions);

			// Create result
			Object[] result = createSuccessResult("subscriptions",
					subscriptionObjects);

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] getPublications(String callerId) {

		// Log
		LOGGER.trace("getPublications request from callerId: " + callerId
				+ " ...");

		try {

			// Get publications
			Map<String, String> publications = slaveAPI
					.getPublications(callerId);

			// Get publications objects
			Object[] publicationsObjects = MapUtil.K_STRING_V_STRING
					.toObjects(publications);

			// Create result
			Object[] result = createSuccessResult("publications",
					publicationsObjects);

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] paramUpdate(String callerId, String parameterKey,
			Integer parameterValue) {
		return paramUpdate(callerId, parameterKey, (Object) parameterValue);
	}

	public Object[] paramUpdate(String callerId, String parameterKey,
			Boolean parameterValue) {
		return paramUpdate(callerId, parameterKey, (Object) parameterValue);
	}

	public Object[] paramUpdate(String callerId, String parameterKey,
			String parameterValue) {
		return paramUpdate(callerId, parameterKey, (Object) parameterValue);
	}

	public Object[] paramUpdate(String callerId, String parameterKey,
			Double parameterValue) {
		return paramUpdate(callerId, parameterKey, (Object) parameterValue);
	}

	public Object[] paramUpdate(String callerId, String parameterKey,
			Date parameterValue) {
		return paramUpdate(callerId, parameterKey, (Object) parameterValue);
	}

	public Object[] paramUpdate(String callerId, String parameterKey,
			byte[] parameterValue) {
		return paramUpdate(callerId, parameterKey, (Object) parameterValue);
	}

	public Object[] paramUpdate(String callerId, String parameterKey,
			Map<?, ?> parameterValue) {
		return paramUpdate(callerId, parameterKey, (Object) parameterValue);
	}

	public Object[] paramUpdate(String callerId, String parameterKey,
			Object parameterValue) {

		// Log
		LOGGER.trace("paramUpdate request from callerId: " + callerId + " ...");

		try {

			// Param update
			slaveAPI.paramUpdate(callerId, parameterKey, parameterValue);

			// Create result
			Object[] result = createIgnoreResult("param updated");

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] publisherUpdate(String callerId, String topic,
			Object[] publishers) {

		// Log
		LOGGER.trace("publisherUpdate request from callerId: " + callerId
				+ " ...");

		try {

			// Get publisher uris
			Set<URI> publisherUris = SetUtil.E_URI.toSet(publishers);

			// Publisher update
			slaveAPI.publisherUpdate(callerId, topic, publisherUris);

			// Create result
			Object[] result = createIgnoreResult("publisher updated");

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	public Object[] requestTopic(String callerId, String topic,
			Object[] protocols) {

		// Log
		LOGGER.trace("requestTopic request from callerId: " + callerId + " ...");

		try {

			// Get protocols
			Set<Protocol> protocolSet = SetUtil.E_PROTOCOL.toSet(protocols);

			// Request topic
			Protocol selectedProtocol = slaveAPI.requestTopic(callerId, topic,
					protocolSet);

			// Create selected protocol
			Object[] selectedProtocolObject = new Object[selectedProtocol
					.getArguments().length + 1];
			selectedProtocolObject[0] = selectedProtocol.getName();

			for (int index = 0; index < selectedProtocol.getArguments().length; index++) {
				selectedProtocolObject[index + 1] = selectedProtocol
						.getArguments()[index];
			}

			// Create result
			Object[] result = createSuccessResult("selected protocol",
					selectedProtocolObject);

			return result;

		} catch (RosApiException ex) {

			// Log
			LOGGER.error("an error occurs", ex);

			// Create result
			Object[] result = createErrorResult(ex);

			return result;

		}

	}

	private Object[] createSuccessResult(String message, Object payload) {

		// Get result
		Object[] result = new Object[] { RosResult.SUCCESS, message, payload };

		return result;

	}

	private Object[] createIgnoreResult(String message) {

		// Get result
		Object[] result = new Object[] { RosResult.SUCCESS, message, 1 };

		return result;

	}

	private Object[] createErrorResult(Exception ex) {

		// Get result
		Object[] result = new Object[] { RosResult.ERROR, ex.getMessage(), 1 };

		return result;

	}

}
