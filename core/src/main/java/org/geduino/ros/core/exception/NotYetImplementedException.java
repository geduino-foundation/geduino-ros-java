package org.geduino.ros.core.exception;


public class NotYetImplementedException extends RosRuntimeException {

	private static final long serialVersionUID = 1L;

	public NotYetImplementedException() {
		super("Sorry, this method is not yet implemented!");
	}

}
