package org.geduino.ros.core.api.model;

import java.io.Serializable;

public abstract class Protocol implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ProtocolType type;

	public Protocol(ProtocolType type) {
		this.type = type;
	}

	public ProtocolType getType() {
		return type;
	}

}
