package org.geduino.ros.tcpros.exception;

import org.geduino.ros.core.exception.RosException;

public class TcpRosException extends RosException {

	private static final long serialVersionUID = 1L;

	public TcpRosException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TcpRosException(String message) {
		super(message);
	}

}
