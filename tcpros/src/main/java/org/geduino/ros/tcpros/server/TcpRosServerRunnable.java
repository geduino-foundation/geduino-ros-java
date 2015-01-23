package org.geduino.ros.tcpros.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.geduino.ros.tcpros.TcpRosConnection;
import org.geduino.ros.tcpros.exception.TcpRosException;
import org.geduino.ros.tcpros.server.exception.TcpRosServerException;

class TcpRosServerRunnable<C extends TcpRosConnection> implements Runnable {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosServerRunnable.class);

	private final TcpRosServerConfig<C> tcpRosServerConfig;

	private final ServerSocket serverSocket;

	TcpRosServerRunnable(TcpRosServerConfig<C> tcpRosServerConfig)
			throws TcpRosServerException {

		// Must be the first since is used to create other objects
		this.tcpRosServerConfig = tcpRosServerConfig;

		this.serverSocket = createServerSocket();

	}

	@Override
	public void run() {

		try {

			// Wait connection
			waitConnection(serverSocket);

		} catch (IOException ex) {

			// Log
			LOGGER.error("rostcp server error", ex);

		}

	}

	int getPort() {
		return serverSocket.getLocalPort();
	}

	public synchronized void stop() throws IOException {

		try {

			// Log
			LOGGER.trace("closing tcpros server socket...");

			// Close server socket
			serverSocket.close();

		} catch (IOException ex) {

			// Log
			LOGGER.error("an error occurs closing tcpros server socket", ex);

			throw ex;

		}

	}

	private ServerSocket createServerSocket() throws TcpRosServerException {

		try {

			// Log
			LOGGER.trace("creating tcpros server socket...");

			// Create server socket
			ServerSocket serverSocket = new ServerSocket(
					tcpRosServerConfig.getPort(),
					tcpRosServerConfig.getBacklog());

			return serverSocket;

		} catch (IOException ex) {

			// Throw exception
			throw new TcpRosServerException(
					"could not create tcpros server runnable", ex);

		}

	}

	private void waitConnection(ServerSocket serverSocket) throws IOException {

		// Log
		LOGGER.trace("waiting tcpros connection on port: "
				+ serverSocket.getLocalPort() + " ...");

		// Accept connection
		Socket socket = serverSocket.accept();

		// Log
		LOGGER.trace("accepted connection from: "
				+ socket.getInetAddress().getHostName());

		try {

			// Get tcp ros connection factory
			TcpRosConnectionFactory<C> tcpRosConnectionFactory = tcpRosServerConfig
					.getTcpRosConnectionFactory();

			if (tcpRosConnectionFactory != null) {

				// Create tcp ros connection
				C tcpRosConnection = tcpRosConnectionFactory.newConnection(
						socket, tcpRosServerConfig);

				TcpRosServerConnectionHandler<C> tcpRosServerConnectionHandler = new TcpRosServerConnectionHandler<C>(
						tcpRosConnection, tcpRosServerConfig);

				// Handle tcp ros server connection
				tcpRosServerConnectionHandler.handle();

			} else {

				//
				LOGGER.error("could not exstabilish connection: no tcpRosConnectionFactory in config");

			}

		} catch (TcpRosException ex) {

			// Log
			LOGGER.error("could not exstabilish connection", ex);

		}

	}

}
