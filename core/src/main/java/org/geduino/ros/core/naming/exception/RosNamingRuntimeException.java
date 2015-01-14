package org.geduino.ros.core.naming.exception;

import org.geduino.ros.core.exception.RosRuntimeException;


public class RosNamingRuntimeException extends RosRuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RosNamingRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosNamingRuntimeException(String message) {
		super(message);
	}

}
