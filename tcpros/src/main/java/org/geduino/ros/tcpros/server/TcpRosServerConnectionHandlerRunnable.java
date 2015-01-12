package org.geduino.ros.tcpros.server;


import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.geduino.ros.core.transport.ConnectionListener;
import org.geduino.ros.tcpros.exception.TcpRosException;

class TcpRosServerConnectionHandlerRunnable implements Runnable {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosServerConnectionHandlerRunnable.class);

	private final String callerId;
	private final TcpRosServerConfig tcpRosServerConfig;
	private final Socket socket;

	public TcpRosServerConnectionHandlerRunnable(String callerId,
			TcpRosServerConfig tcpRosServerConfig, Socket socket) {
		this.callerId = callerId;
		this.tcpRosServerConfig = tcpRosServerConfig;
		this.socket = socket;
	}

	@Override
	public void run() {

		try {

			// Create tcp ros server connection connection
			TcpRosServerConnection tcpRosServerConnection = new TcpRosServerConnection(
					callerId, socket);

			// Get connection listeners
			List<ConnectionListener> connectionListeners = tcpRosServerConfig
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
			TcpRosServerConnection tcpRosServerConnection,
			List<ConnectionListener> connectionListeners) {

		// Log
		LOGGER.trace("firing incoming publisher connection to "
				+ connectionListeners.size() + " listeners...");

		for (Iterator<ConnectionListener> iterator = connectionListeners
				.iterator(); iterator.hasNext();) {

			// Get next connection listener
			ConnectionListener connectionListener = iterator.next();

			// Fire incoming publisher connection
			fireIncomingPublisherConnection(tcpRosServerConnection,
					connectionListener);

		}

	}

	private void fireIncomingServiceConnection(
			TcpRosServerConnection tcpRosServerConnection,
			List<ConnectionListener> connectionListeners) {

		// Log
		LOGGER.trace("firing incoming service connection to "
				+ connectionListeners.size() + " listeners...");

		for (Iterator<ConnectionListener> iterator = connectionListeners
				.iterator(); iterator.hasNext();) {

			// Get next connection listener
			ConnectionListener connectionListener = iterator.next();

			// Fire incoming service connection
			fireIncomingServiceConnection(tcpRosServerConnection,
					connectionListener);

		}

	}

	private void fireIncomingPublisherConnection(
			TcpRosServerConnection tcpRosServerConnection,
			ConnectionListener connectionListener) {

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
			TcpRosServerConnection tcpRosServerConnection,
			ConnectionListener connectionListener) {

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
