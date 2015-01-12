package org.geduino.ros.tcpros.server.exception;

import org.geduino.ros.tcpros.exception.TcpRosException;

public class TcpRosServerException extends TcpRosException {

	private static final long serialVersionUID = 1L;

	public TcpRosServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public TcpRosServerException(String message) {
		super(message);
	}

}
