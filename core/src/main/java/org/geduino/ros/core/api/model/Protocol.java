package org.geduino.ros.core.api.model;

import java.io.Serializable;

public class Protocol implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;
	private final Object[] arguments;

	public Protocol(String name, Object[] arguments) {
		this.name = name;
		this.arguments = arguments;
	}

	public String getName() {
		return name;
	}

	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public String toString() {

		StringBuffer stringBuffer = new StringBuffer();

		// Init string
		stringBuffer.append("Protocol [name=" + name + ", arguments={");

		for (int index = 0; index < arguments.length; index++) {

			if (index > 0) {

				// Add separation
				stringBuffer.append(", ");

			}

			// Add argument
			stringBuffer.append(arguments[index]);

		}

		// Close string
		stringBuffer.append("}]");

		return stringBuffer.toString();

	}

}
