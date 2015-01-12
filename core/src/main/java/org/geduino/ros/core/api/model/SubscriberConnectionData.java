package org.geduino.ros.core.api.model;

import java.io.Serializable;

public class SubscriberConnectionData implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String connectionId;
	private final int byteReceived;
	private final int dropEstimate;
	private boolean connected;

	public SubscriberConnectionData(String connectionId, int byteReceived,
			int dropEstimate, boolean connected) {

		this.connectionId = connectionId;
		this.byteReceived = byteReceived;
		this.dropEstimate = dropEstimate;
		this.connected = connected;

	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public int getByteReceived() {
		return byteReceived;
	}

	public int getDropEstimate() {
		return dropEstimate;
	}

	@Override
	public String toString() {
		return "SubConnectionData [connectionId=" + connectionId
				+ ", byteReceived=" + byteReceived + ", dropEstimate="
				+ dropEstimate + ", connected=" + connected + "]";
	}

}
