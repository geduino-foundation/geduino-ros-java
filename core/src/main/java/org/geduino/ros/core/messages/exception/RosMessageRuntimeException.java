package org.geduino.ros.core.messages.exception;

import org.geduino.ros.core.exception.RosRuntimeException;


public class RosMessageRuntimeException extends RosRuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RosMessageRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosMessageRuntimeException(String message) {
		super(message);
	}

}
