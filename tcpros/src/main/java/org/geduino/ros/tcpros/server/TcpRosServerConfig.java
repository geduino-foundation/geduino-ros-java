package org.geduino.ros.tcpros.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.transport.model.ConnectionListener;

public class TcpRosServerConfig<T extends Message, K extends Message> {

	private int port;
	private int backlog;

	private final List<ConnectionListener<T, K>> connectionListeners;

	public TcpRosServerConfig() {

		// Set default value
		port = 0;
		backlog = 1;

		connectionListeners = new ArrayList<ConnectionListener<T, K>>();

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

	public void addConnectionListener(ConnectionListener<T, K> connectionListener) {
		connectionListeners.add(connectionListener);
	}

	public boolean removeConnectionListener(
			ConnectionListener<T, K> connectionListener) {
		return connectionListeners.remove(connectionListener);
	}

	public void removeConnectionListeners() {
		connectionListeners.clear();
	}

	public List<ConnectionListener<T, K>> getConnectionListeners() {

		// Create unmodifiable copy of connection listeners
		List<ConnectionListener<T, K>> unmodifiableConnectionListeners = Collections
				.unmodifiableList(connectionListeners);

		return unmodifiableConnectionListeners;

	}

}
