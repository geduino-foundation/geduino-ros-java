package org.geduino.ros.core.transport.exception;

import org.geduino.ros.core.exception.RosException;

public class RosTransportSerializationException extends RosException {

	private static final long serialVersionUID = 1L;

	public RosTransportSerializationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RosTransportSerializationException(String message) {
		super(message);
	}

}
