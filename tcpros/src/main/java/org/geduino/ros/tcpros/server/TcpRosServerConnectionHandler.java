package org.geduino.ros.tcpros.server;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.geduino.ros.core.transport.model.ConnectionListener;
import org.geduino.ros.tcpros.TcpRosConnection;

class TcpRosServerConnectionHandler<C extends TcpRosConnection> {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosServerConnectionHandler.class);

	private final C tcpRosServerConnection;
	private final TcpRosServerConfig<C> tcpRosServerConfig;

	TcpRosServerConnectionHandler(C tcpRosServerConnection,
			TcpRosServerConfig<C> tcpRosServerConfig) {
		this.tcpRosServerConnection = tcpRosServerConnection;
		this.tcpRosServerConfig = tcpRosServerConfig;
	}

	void handle() {

		// Get connection listeners
		List<ConnectionListener<C>> connectionListeners = tcpRosServerConfig
				.getConnectionListeners();

		// Fire incoming publisher connection
		fireIncomingPublisherConnection(tcpRosServerConnection,
				connectionListeners);

	}

	private void fireIncomingPublisherConnection(C tcpRosConnection,
			List<ConnectionListener<C>> connectionListeners) {

		// Log
		LOGGER.trace("firing incoming connection to "
				+ connectionListeners.size() + " listeners...");

		for (Iterator<ConnectionListener<C>> iterator = connectionListeners
				.iterator(); iterator.hasNext();) {

			// Get next connection listener
			ConnectionListener<C> connectionListener = iterator.next();

			// Fire incoming connection
			fireIncomingConnection(tcpRosConnection, connectionListener);

		}

	}

	private void fireIncomingConnection(C tcpRosConnection,
			ConnectionListener<C> connectionListener) {

		try {

			// Fire incomimg connection
			connectionListener.incomingConnection(tcpRosConnection);

		} catch (Exception ex) {

			// Log
			LOGGER.error("an error occurs firing incoming connection", ex);

		}

	}

}
