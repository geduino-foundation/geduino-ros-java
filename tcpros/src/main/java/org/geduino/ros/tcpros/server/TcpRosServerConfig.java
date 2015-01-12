package org.geduino.ros.tcpros.server;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.geduino.ros.core.transport.ConnectionListener;

public class TcpRosServerConfig {

	private int port;
	private int backlog;
	private int threadPoolSize;

	private final List<ConnectionListener> connectionListeners;

	public TcpRosServerConfig() {

		// Set default value
		port = 11322;
		backlog = 50;
		threadPoolSize = 50;

		connectionListeners = new ArrayList<ConnectionListener>();

	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	public int getThreadPoolSize() {
		return threadPoolSize;
	}

	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

	public void addConnectionListener(ConnectionListener connectionListener) {
		connectionListeners.add(connectionListener);
	}

	public boolean removeConnectionListener(
			ConnectionListener connectionListener) {
		return connectionListeners.remove(connectionListener);
	}

	public void removeConnectionListeners() {
		connectionListeners.clear();
	}

	public List<ConnectionListener> getConnectionListeners() {

		// Create unmodifiable copy of connection listeners
		List<ConnectionListener> unmodifiableConnectionListeners = Collections
				.unmodifiableList(connectionListeners);

		return unmodifiableConnectionListeners;

	}

}
