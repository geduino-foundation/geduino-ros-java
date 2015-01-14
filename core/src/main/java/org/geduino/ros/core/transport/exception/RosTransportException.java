package org.geduino.ros.core.transport.exception;

import org.geduino.ros.core.exception.RosException;

public class RosTransportException extends RosException {

	private static final long serialVersionUID = 1L;

	public RosTransportException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RosTransportException(String message) {
		super(message);
	}

}
