package org.geduino.ros.core.messages.model;

import org.geduino.ros.core.naming.model.Name;

public enum PrimitiveFieldType implements FieldType {

	BOOL, INT8, UINT8, INT16, UINT16, INT32, UINT32, INT64, UINT64, FLOAT32, FLOAT64, STRING, BYTE, CHAR, TIME, DURATION;

	@Override
	public Name getSimpleName() {

		// Get simple name
		Name simpleName = Name.parseName(this.name().toLowerCase());

		return simpleName;

	}

	public String toString() {

		// Get string
		String string = this.name().toLowerCase();

		return string;

	}

}
