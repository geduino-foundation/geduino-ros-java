package org.geduino.ros.core.messages.exception;

public class RosMessageSerializationException extends RosMessageException {

	private static final long serialVersionUID = 1L;

	public RosMessageSerializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosMessageSerializationException(String message) {
		super(message);
	}

}
