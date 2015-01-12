package org.geduino.ros.tcpros.exception;

public class TcpRosHeaderSerializationException extends
		TcpRosHandshakeException {

	private static final long serialVersionUID = 1L;

	public TcpRosHeaderSerializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TcpRosHeaderSerializationException(String message) {
		super(message);
	}

}
