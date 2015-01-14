package org.geduino.ros.messages.description.exception;

import org.geduino.ros.core.messages.exception.RosMessageException;

public class RosMessageDescriptionException extends RosMessageException {

	private static final long serialVersionUID = 1L;

	public RosMessageDescriptionException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosMessageDescriptionException(String message) {
		super(message);
	}

}
