package org.geduino.ros.tcpros.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.geduino.ros.tcpros.server.exception.TcpRosServerException;

class TcpRosServerRunnable implements Runnable {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosServerRunnable.class);

	private final String callerId;
	private final TcpRosServerConfig tcpRosServerConfig;

	private final ServerSocket serverSocket;
	private final ExecutorService executorService;

	private boolean stopped;

	TcpRosServerRunnable(String callerId, TcpRosServerConfig tcpRosServerConfig)
			throws TcpRosServerException {

		this.callerId = callerId;

		// Must be the first since is used to create other objects
		this.tcpRosServerConfig = tcpRosServerConfig;

		this.serverSocket = createServerSocket();
		this.executorService = Executors.newFixedThreadPool(tcpRosServerConfig
				.getThreadPoolSize());
		this.stopped = false;

	}

	@Override
	public void run() {

		try {

			while (!stopped) {

				// Wait connection
				waitConnection(serverSocket);

			}

		} catch (IOException ex) {

			// Log
			LOGGER.error("rostcp server error", ex);

		}

	}

	public synchronized void stop() throws IOException {

		if (!stopped) {

			// Set stopped
			stopped = true;

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

			// Log
			LOGGER.trace("shutting down tcpros connection executors...");

			// Shutdown executor
			executorService.shutdown();

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
		LOGGER.trace("waiting tcpros connection...");

		// Accept connection
		Socket socket = serverSocket.accept();

		// Log
		LOGGER.trace("accepted connection from: "
				+ socket.getInetAddress().getHostName() + " on local port: "
				+ socket.getLocalPort());

		// Create tcp ros server connection handler
		TcpRosServerConnectionHandlerRunnable tcpRosServerConnectionHandlerRunnable = new TcpRosServerConnectionHandlerRunnable(
				callerId, tcpRosServerConfig, socket);

		// Execute tcp ros server connection handler
		executorService.execute(tcpRosServerConnectionHandlerRunnable);

	}

}
