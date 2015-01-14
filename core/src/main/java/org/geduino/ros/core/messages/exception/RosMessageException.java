package org.geduino.ros.core.messages.exception;

import org.geduino.ros.core.exception.RosException;

public class RosMessageException extends RosException {

	private static final long serialVersionUID = 1L;

	public RosMessageException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RosMessageException(String message) {
		super(message);
	}

}
