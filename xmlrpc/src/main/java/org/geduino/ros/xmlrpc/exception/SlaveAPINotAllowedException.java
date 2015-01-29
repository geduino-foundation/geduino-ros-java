package org.geduino.ros.xmlrpc.exception;

import org.geduino.ros.core.api.exception.RosApiException;

public class SlaveAPINotAllowedException extends RosApiException {

	private static final long serialVersionUID = 1L;

	public SlaveAPINotAllowedException() {
		super("only MASTER is allowed to invoke this");
	}

}
