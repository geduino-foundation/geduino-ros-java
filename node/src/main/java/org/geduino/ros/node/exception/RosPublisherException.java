package org.geduino.ros.node.exception;


public class RosPublisherException extends RosNodeException {

	private static final long serialVersionUID = 1L;

	public RosPublisherException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosPublisherException(String message) {
		super(message);
	}

}
