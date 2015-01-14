package org.geduino.ros.node.exception;

import org.geduino.ros.core.exception.RosRuntimeException;

public class RosNodeRuntimeException extends RosRuntimeException {

	private static final long serialVersionUID = 1L;

	public RosNodeRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RosNodeRuntimeException(String message) {
		super(message);
	}

}
