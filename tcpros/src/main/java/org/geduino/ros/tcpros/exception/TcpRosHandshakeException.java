package org.geduino.ros.tcpros.exception;

import org.geduino.ros.tcpros.exception.TcpRosException;

public class TcpRosHandshakeException extends TcpRosException {

	private static final long serialVersionUID = 1L;

	public TcpRosHandshakeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TcpRosHandshakeException(String message) {
		super(message);
	}

}
