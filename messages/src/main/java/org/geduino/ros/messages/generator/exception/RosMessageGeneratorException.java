package org.geduino.ros.messages.generator.exception;

import org.geduino.ros.core.messages.exception.RosMessageException;

public class RosMessageGeneratorException extends RosMessageException {

	private static final long serialVersionUID = 1L;

	public RosMessageGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosMessageGeneratorException(String message) {
		super(message);
	}

}
