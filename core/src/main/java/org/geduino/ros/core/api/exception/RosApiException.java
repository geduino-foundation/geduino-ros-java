package org.geduino.ros.core.api.exception;

import org.geduino.ros.core.exception.RosException;

public class RosApiException extends RosException {

	private static final long serialVersionUID = 1L;

	public RosApiException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RosApiException(String message) {
		super(message);
	}

}
