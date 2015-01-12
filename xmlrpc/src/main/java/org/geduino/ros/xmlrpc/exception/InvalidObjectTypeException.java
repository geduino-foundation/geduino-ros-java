package org.geduino.ros.xmlrpc.exception;

import org.geduino.ros.core.api.exception.RosApiException;

public class InvalidObjectTypeException extends RosApiException {

	private static final long serialVersionUID = 1L;

	public InvalidObjectTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidObjectTypeException(String message) {
		super(message);
	}

}
