package org.geduino.ros.messages.description.model;

public enum PrimitiveFieldType implements FieldType {

	BOOL, INT8, UINT8, INT16, UINT16, INT32, UINT32, INT64, UINT64, FLOAT32, FLOAT64, STRING, BYTE, CHAR, TIME, DURATION;

	public String toString() {

		// Get string
		String string = this.name().toLowerCase();

		return string;

	}

}
