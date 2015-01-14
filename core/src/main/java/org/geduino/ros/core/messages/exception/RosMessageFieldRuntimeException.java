package org.geduino.ros.core.messages.exception;

public class RosMessageFieldRuntimeException extends RosMessageRuntimeException {

	private static final long serialVersionUID = 1L;

	public RosMessageFieldRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosMessageFieldRuntimeException(String message) {
		super(message);
	}

}
