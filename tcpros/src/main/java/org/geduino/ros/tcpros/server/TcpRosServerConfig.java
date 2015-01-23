package org.geduino.ros.tcpros.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.model.ConnectionListener;
import org.geduino.ros.tcpros.TcpRosConnection;

public class TcpRosServerConfig<C extends TcpRosConnection> {

	private final GlobalName callerId;
	private final int port;
	private final int backlog;
	private final TcpRosConnectionFactory<C> tcpRosConnectionFactory;
	private final List<ConnectionListener<C>> connectionListeners;

	public TcpRosServerConfig(GlobalName callerId, int port, int backLog, TcpRosConnectionFactory<C> tcpRosConnectionFactory) {

		this.callerId = callerId;
		this.port = port;
		this.backlog = backLog;
		this.tcpRosConnectionFactory = tcpRosConnectionFactory;

		this.connectionListeners = new ArrayList<ConnectionListener<C>>();

	}

	public GlobalName getCallerId() {
		return callerId;
	}

	public int getPort() {
		return port;
	}

	public int getBacklog() {
		return backlog;
	}
	
	public TcpRosConnectionFactory<C> getTcpRosConnectionFactory() {
		return tcpRosConnectionFactory;
	}

	public void addConnectionListener(ConnectionListener<C> connectionListener) {
		connectionListeners.add(connectionListener);
	}

	public boolean removeConnectionListener(
			ConnectionListener<C> connectionListener) {
		return connectionListeners.remove(connectionListener);
	}

	public void removeConnectionListeners() {
		connectionListeners.clear();
	}

	public List<ConnectionListener<C>> getConnectionListeners() {

		// Create unmodifiable copy of connection listeners
		List<ConnectionListener<C>> unmodifiableConnectionListeners = Collections
				.unmodifiableList(connectionListeners);

		return unmodifiableConnectionListeners;

	}

}
