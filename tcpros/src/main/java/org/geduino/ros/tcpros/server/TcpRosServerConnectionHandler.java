package org.geduino.ros.tcpros.server;


import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.model.ConnectionListener;
import org.geduino.ros.tcpros.exception.TcpRosException;

class TcpRosServerConnectionHandler<T extends Message, K extends Message>  {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosServerConnectionHandler.class);

	private final GlobalName callerId;
	private final TcpRosServerConfig<T, K> tcpRosServerConfig;
	private final Socket socket;

	TcpRosServerConnectionHandler(GlobalName callerId,
			TcpRosServerConfig<T, K> tcpRosServerConfig, Socket socket) {
		this.callerId = callerId;
		this.tcpRosServerConfig = tcpRosServerConfig;
		this.socket = socket;
	}

	void handle() {

		try {

			// Create tcp ros server connection connection
			TcpRosServerConnection<T, K> tcpRosServerConnection = new TcpRosServerConnection<T, K>(
					callerId, socket);

			// Get connection listeners
			List<ConnectionListener<T, K>> connectionListeners = tcpRosServerConfig
					.getConnectionListeners();

			if (tcpRosServerConnection.isPublisherConnection()) {

				// Fire incoming publisher connection
				fireIncomingPublisherConnection(tcpRosServerConnection,
						connectionListeners);

			} else if (tcpRosServerConnection.isServiceConnection()) {

				// Fire incoming service connection
				fireIncomingServiceConnection(tcpRosServerConnection,
						connectionListeners);

			} else {

				// Log
				LOGGER.warn("unable to handle connection: "
						+ tcpRosServerConnection);

			}

		} catch (TcpRosException ex) {

			// Log
			LOGGER.error("an error occurs handling connection", ex);

		} catch (IOException ex) {

			// Log
			LOGGER.error("an error occurs handling connection", ex);

		}

	}

	private void fireIncomingPublisherConnection(
			TcpRosServerConnection<T, K> tcpRosServerConnection,
			List<ConnectionListener<T, K>> connectionListeners) {

		// Log
		LOGGER.trace("firing incoming publisher connection to "
				+ connectionListeners.size() + " listeners...");

		for (Iterator<ConnectionListener<T, K>> iterator = connectionListeners
				.iterator(); iterator.hasNext();) {

			// Get next connection listener
			ConnectionListener<T, K> connectionListener = iterator.next();

			// Fire incoming publisher connection
			fireIncomingPublisherConnection(tcpRosServerConnection,
					connectionListener);

		}

	}

	private void fireIncomingServiceConnection(
			TcpRosServerConnection<T, K> tcpRosServerConnection,
			List<ConnectionListener<T, K>> connectionListeners) {

		// Log
		LOGGER.trace("firing incoming service connection to "
				+ connectionListeners.size() + " listeners...");

		for (Iterator<ConnectionListener<T, K>> iterator = connectionListeners
				.iterator(); iterator.hasNext();) {

			// Get next connection listener
			ConnectionListener<T, K> connectionListener = iterator.next();

			// Fire incoming service connection
			fireIncomingServiceConnection(tcpRosServerConnection,
					connectionListener);

		}

	}

	private void fireIncomingPublisherConnection(
			TcpRosServerConnection<T, K> tcpRosServerConnection,
			ConnectionListener<T, K> connectionListener) {

		try {

			// Fire incomimg publisher connection
			connectionListener
					.incomingPublisherConnection(tcpRosServerConnection);

		} catch (Exception ex) {

			// Log
			LOGGER.error(
					"an error occurs firing incoming publisher connection", ex);

		}

	}

	private void fireIncomingServiceConnection(
			TcpRosServerConnection<T, K> tcpRosServerConnection,
			ConnectionListener<T, K> connectionListener) {

		try {

			// Fire incomimg service connection
			connectionListener
					.incomingServiceConnection(tcpRosServerConnection);

		} catch (Exception ex) {

			// Log
			LOGGER.error("an error occurs firing incoming service connection",
					ex);

		}

	}

}
