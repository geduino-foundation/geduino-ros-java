package org.geduino.ros.tcpros.server;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.tcpros.server.exception.TcpRosServerException;

public class TcpRosServer<T extends Message, K extends Message> {

	private static final Logger LOGGER = Logger.getLogger(TcpRosServer.class);

	private final GlobalName callerId;
	private final TcpRosServerConfig<T, K> tcpRosServerConfig;

	private TcpRosServerThread<T, K> tcpRosServerThread;
	
	private int effectivePort;

	public TcpRosServer(GlobalName callerId) {
		this.callerId = callerId;
		this.tcpRosServerConfig = new TcpRosServerConfig<T, K>();
	}

	public GlobalName getCallerId() {
		return callerId;
	}

	public TcpRosServerConfig<T, K> getTcpRosServerConfig() {
		return tcpRosServerConfig;
	}

	public synchronized void start() throws TcpRosServerException {

		if (tcpRosServerThread == null) {

			// Create tcp ros server runnable
			TcpRosServerRunnable<T, K> tcpRosServerRunnable = new TcpRosServerRunnable<T, K>(
					callerId, tcpRosServerConfig);
			
			// Get effective port
			effectivePort = tcpRosServerRunnable.getPort();

			// Create tcp ros server thread
			tcpRosServerThread = new TcpRosServerThread<T, K>(tcpRosServerRunnable);

			// Log
			LOGGER.trace("starting tcpros server thread...");

			// Start tcp ros server thread
			tcpRosServerThread.start();

			// Log
			LOGGER.debug("tcpros server started on port: "
					+ effectivePort);

		} else {

			// Log
			LOGGER.warn("could not start tcpros server: already started");

		}

	}
	
	public int getEffectivePort() {
		return effectivePort;
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
