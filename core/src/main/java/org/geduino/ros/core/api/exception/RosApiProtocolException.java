package org.geduino.ros.core.api.exception;

public class RosApiProtocolException extends RosApiException {

	private static final long serialVersionUID = 1L;

	public RosApiProtocolException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RosApiProtocolException(String message) {
		super(message);
	}

}
