package org.geduino.ros.tcpros.client.exception;

import org.geduino.ros.tcpros.exception.TcpRosException;

public class TcpRosClientException extends TcpRosException {

	private static final long serialVersionUID = 1L;

	public TcpRosClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public TcpRosClientException(String message) {
		super(message);
	}

}
