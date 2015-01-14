package org.geduino.ros.xmlrpc.server;

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
import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.core.api.model.TcpRosProtocol;
import org.geduino.ros.core.api.model.UdpRosProtocol;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.xmlrpc.assembler.BusInfoObjectsAssembler;
import org.geduino.ros.xmlrpc.assembler.BusStatsObjectsAssembler;
import org.geduino.ros.xmlrpc.assembler.util.MapToObjectAssembler;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToSetAssembler;
import org.geduino.ros.xmlrpc.model.RosResult;

public class XmlRpcSlaveAPIServlet {

	private static final Logger LOGGER = Logger
			.getLogger(XmlRpcSlaveAPIServlet.class);

	private final SlaveAPI slaveAPI;

	public XmlRpcSlaveAPIServlet(SlaveAPI slaveAPI) {
		this.slaveAPI = slaveAPI;
	}

	public Object[] getBusStats(String callerId) {

		// Log
		LOGGER.trace("getBusStats request from callerId: " + callerId + " ...");

		try {

			// Get bus stats
			BusStats busStats = slaveAPI.getBusStats((GlobalName) Name
					.parseName(callerId));

			// Assemble bus stats object
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
			Set<BusInfo> busInfos = slaveAPI.getBusInfo((GlobalName) Name
					.parseName(callerId));

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
			URI masterUri = slaveAPI.getMasterUri((GlobalName) Name
					.parseName(callerId));

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
			slaveAPI.shutdown((GlobalName) Name.parseName(callerId), message);

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
			int pid = slaveAPI.getPid((GlobalName) Name.parseName(callerId));

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
			Map<GlobalName, MessageName> subscriptions = slaveAPI
					.getSubscriptions((GlobalName) Name.parseName(callerId));

			// Assemble subscription objects
			Object[] subscriptionObjects = MapToObjectAssembler.K_GLOBAL_NAME_V_MESSAGE_NAME
					.assemble(subscriptions);

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
			Map<GlobalName, MessageName> publications = slaveAPI
					.getPublications((GlobalName) Name.parseName(callerId));

			// Assemble publications objects
			Object[] publicationsObjects = MapToObjectAssembler.K_GLOBAL_NAME_V_MESSAGE_NAME
					.assemble(publications);

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
			slaveAPI.paramUpdate((GlobalName) Name.parseName(callerId),
					(GlobalName) Name.parseName(parameterKey), parameterValue);

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

			// Assemble publisher uris
			Set<URI> publisherUris = ObjectToSetAssembler.URI
					.assemble(publishers);

			// Publisher update
			slaveAPI.publisherUpdate((GlobalName) Name.parseName(callerId),
					(GlobalName) Name.parseName(topic), publisherUris);

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

			// Get protocol types
			Set<ProtocolType> protocolTypes = ObjectToSetAssembler.PROTOCOL_TYPE
					.assemble(protocols);

			// Request topic
			Protocol selectedProtocol = slaveAPI.requestTopic(
					(GlobalName) Name.parseName(callerId),
					(GlobalName) Name.parseName(topic), protocolTypes);

			if (selectedProtocol instanceof TcpRosProtocol) {

				// Cast to tcpros protocol
				TcpRosProtocol tcpRosProtocol = (TcpRosProtocol) selectedProtocol;

				// Create selected protocol
				Object[] selectedProtocolObject = new Object[3];
				selectedProtocolObject[0] = ProtocolType.TCPROS.toString();
				selectedProtocolObject[1] = tcpRosProtocol.getHost();
				selectedProtocolObject[2] = new Integer(
						tcpRosProtocol.getPort());

				// Create result
				Object[] result = createSuccessResult("selected protocol",
						selectedProtocolObject);

				return result;

			} else if (selectedProtocol instanceof UdpRosProtocol) {

				// Create selected protocol
				Object[] selectedProtocolObject = new Object[1];
				selectedProtocolObject[0] = ProtocolType.UDPROS.toString();

				// Create result
				Object[] result = createSuccessResult("selected protocol",
						selectedProtocolObject);

				return result;

			} else {

				// Throw exception
				throw new RosApiException("unknow transport protocol name: "
						+ selectedProtocol);

			}

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
