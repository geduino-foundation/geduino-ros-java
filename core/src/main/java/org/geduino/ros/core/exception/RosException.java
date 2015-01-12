package org.geduino.ros.core.exception;

public class RosException extends Exception {

	private static final long serialVersionUID = 1L;

	public RosException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosException(String message) {
		super(message);
	}

}
