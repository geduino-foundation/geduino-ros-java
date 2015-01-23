package org.geduino.ros.tcpros.server;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.geduino.ros.tcpros.TcpRosConnection;

class TcpRosServerThread<C extends TcpRosConnection> extends Thread {

	private static final Logger LOGGER = Logger.getLogger(TcpRosServerThread.class);

	private final TcpRosServerRunnable<C> tcpRosServerRunnable;

	TcpRosServerThread(TcpRosServerRunnable<C> tcpRosServerRunnable) {

		super(tcpRosServerRunnable, "tcpros-server");

		this.tcpRosServerRunnable = tcpRosServerRunnable;

	}

	@Override
	public void interrupt() {

		try {

			// Stop tcp ros server runnable
			tcpRosServerRunnable.stop();

		} catch (IOException ex) {

			// Log
			LOGGER.warn("an error occurs stooping tcpros server runnable", ex);

		}

		super.interrupt();

	}

}
