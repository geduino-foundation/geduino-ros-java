package org.geduino.ros.core.api.model;

import java.io.Serializable;
import java.net.URI;

public class BusInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String connectionId;
	private final URI destinationId;
	private final Direction direction;
	private final Transport transport;
	private final String topicName;
	private final boolean connected;

	public BusInfo(String connectionId, URI destinationId, Direction direction,
			Transport transport, String topicName, boolean connected) {

		this.connectionId = connectionId;
		this.destinationId = destinationId;
		this.direction = direction;
		this.transport = transport;
		this.topicName = topicName;
		this.connected = connected;

	}

	public String getConnectionId() {
		return connectionId;
	}

	public URI getDestinationId() {
		return destinationId;
	}

	public Direction getDirection() {
		return direction;
	}

	public Transport getTransport() {
		return transport;
	}

	public String getTopicName() {
		return topicName;
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	public String toString() {
		return "BusInfo [connectionId=" + connectionId + ", destinationId="
				+ destinationId + ", direction=" + direction + ", transport="
				+ transport + ", topicName=" + topicName + ", connected="
				+ connected + "]";
	}

}
