package org.geduino.ros.core.naming.exception;

import org.geduino.ros.core.exception.RosException;


public class RosNamingException extends RosException {

	private static final long serialVersionUID = 1L;
	
	public RosNamingException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosNamingException(String message) {
		super(message);
	}

}
