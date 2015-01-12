package org.geduino.ros.core.api.model;

import java.io.Serializable;

public class PublisherConnectionData implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String connectionId;
	private final int byteSent;
	private final int numSent;
	private final boolean connected;

	public PublisherConnectionData(String connectionId, int byteSent, int numSent,
			boolean connected) {

		this.connectionId = connectionId;
		this.byteSent = byteSent;
		this.numSent = numSent;
		this.connected = connected;

	}

	public String getConnectionId() {
		return connectionId;
	}

	public int getByteSent() {
		return byteSent;
	}

	public int getNumSent() {
		return numSent;
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	public String toString() {
		return "PubConnectionData [connectionId=" + connectionId
				+ ", byteSent=" + byteSent + ", numSent=" + numSent
				+ ", connected=" + connected + "]";
	}

}
