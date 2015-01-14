package org.geduino.ros.core.naming.model;

import org.geduino.ros.core.naming.exception.RosNamingRuntimeException;

public class RelativeName extends BaseName {

	private static final long serialVersionUID = 1L;

	public RelativeName(String relativeName) {
		super(relativeName);
	}

	@Override
	protected void validate(String string) {

		if (!Name.isRelative(string)) {

			// Throw exception
			throw new RosNamingRuntimeException("invalid relative name: "
					+ string);

		}

	}
	
}
