package org.geduino.ros.tcpros.server;

import org.apache.log4j.Logger;
import org.geduino.ros.tcpros.TcpRosConnection;
import org.geduino.ros.tcpros.server.exception.TcpRosServerException;

public class TcpRosServer<C extends TcpRosConnection> {

	private static final Logger LOGGER = Logger.getLogger(TcpRosServer.class);

	private final TcpRosServerConfig<C> tcpRosServerConfig;

	private TcpRosServerThread<C> tcpRosServerThread;
	
	private int effectivePort;

	public TcpRosServer(TcpRosServerConfig<C> tcpRosServerConfig) {
		this.tcpRosServerConfig = tcpRosServerConfig;
	}

	public synchronized void start() throws TcpRosServerException {

		if (tcpRosServerThread == null) {

			// Create tcp ros server runnable
			TcpRosServerRunnable<C> tcpRosServerRunnable = new TcpRosServerRunnable<C>(tcpRosServerConfig);
			
			// Get effective port
			effectivePort = tcpRosServerRunnable.getPort();

			// Create tcp ros server thread
			tcpRosServerThread = new TcpRosServerThread<C>(tcpRosServerRunnable);

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
