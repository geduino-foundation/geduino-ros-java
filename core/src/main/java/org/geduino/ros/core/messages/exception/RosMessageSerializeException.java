package org.geduino.ros.core.messages.exception;

public class RosMessageSerializeException extends RosMessageException {

	private static final long serialVersionUID = 1L;

	public RosMessageSerializeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosMessageSerializeException(String message) {
		super(message);
	}

}
