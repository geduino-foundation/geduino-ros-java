package org.geduino.ros.node.exception;


public class RosSubscriberException extends RosNodeException {

	private static final long serialVersionUID = 1L;

	public RosSubscriberException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosSubscriberException(String message) {
		super(message);
	}

}
