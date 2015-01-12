package org.geduino.ros.core.exception;

public class RosRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RosRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosRuntimeException(String message) {
		super(message);
	}

}
