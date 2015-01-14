package org.geduino.ros.node.exception;

import org.geduino.ros.core.exception.RosException;

public class RosNodeException extends RosException {

	private static final long serialVersionUID = 1L;

	public RosNodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosNodeException(String message) {
		super(message);
	}

}
