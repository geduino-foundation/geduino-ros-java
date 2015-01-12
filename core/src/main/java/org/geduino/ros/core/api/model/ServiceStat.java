package org.geduino.ros.core.api.model;

import java.io.Serializable;

public class ServiceStat implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int numRequest;
	private final int byteReceived;
	private final int byteSent;

	public ServiceStat(int numRequest, int byteReceived, int byteSent) {

		this.numRequest = numRequest;
		this.byteReceived = byteReceived;
		this.byteSent = byteSent;

	}

	public int getNumRequest() {
		return numRequest;
	}

	public int getByteReceived() {
		return byteReceived;
	}

	public int getByteSent() {
		return byteSent;
	}

	@Override
	public String toString() {
		return "ServiceStats [numRequest=" + numRequest + ", byteReceived="
				+ byteReceived + ", byteSent=" + byteSent + "]";
	}

}
