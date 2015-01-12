package org.geduino.ros.tcpros.server;


import org.apache.log4j.Logger;
import org.geduino.ros.tcpros.server.exception.TcpRosServerException;

public class TcpRosServer {

	private static final Logger LOGGER = Logger.getLogger(TcpRosServer.class);

	private final String callerId;
	private final TcpRosServerConfig tcpRosServerConfig;

	private TcpRosServerThread tcpRosServerThread;

	public TcpRosServer(String callerId) {
		this.callerId = callerId;
		this.tcpRosServerConfig = new TcpRosServerConfig();
	}

	public String getCallerId() {
		return callerId;
	}

	public TcpRosServerConfig getTcpRosServerConfig() {
		return tcpRosServerConfig;
	}

	public synchronized void start() throws TcpRosServerException {

		if (tcpRosServerThread == null) {

			// Create tcp ros server runnable
			TcpRosServerRunnable tcpRosServerRunnable = new TcpRosServerRunnable(
					callerId, tcpRosServerConfig);

			// Create tcp ros server thread
			tcpRosServerThread = new TcpRosServerThread(tcpRosServerRunnable);

			// Log
			LOGGER.trace("starting tcpros server thread...");

			// Start tcp ros server thread
			tcpRosServerThread.start();

			// Log
			LOGGER.debug("tcpros server started on port: "
					+ tcpRosServerConfig.getPort());

		} else {

			// Log
			LOGGER.warn("could not start tcpros server: already started");

		}

	}

	public synchronized void stop() {

		if (tcpRosServerThread != null) {

			// Log
			LOGGER.trace("interrupting tcpros server thread...");

			// Interrupt tcpros server thread
			tcpRosServerThread.interrupt();

		} else {

			// Log
			LOGGER.warn("could not satop tcpros server: not started");

		}

	}

	public synchronized void join() throws InterruptedException {

		if (tcpRosServerThread != null) {

			// Log
			LOGGER.trace("join tcpros server thread...");

			// Join tcpros server thread
			tcpRosServerThread.join();

		} else {

			// Log
			LOGGER.warn("could not join tcpros server: not started");

		}

	}

}
