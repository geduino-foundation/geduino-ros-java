package org.geduino.ros.core.messages.model;

import java.math.BigInteger;

public enum DataType {

	BOOL(1, boolean.class),
	INT8(1, int.class),
	BYTE(1, int.class),
	UINT8(1, int.class), 
	CHAR(1, int.class), 
	INT16(2, int.class),
	UINT16(2, int.class), 
	INT32(4, int.class), 
	UINT32(4, long.class), 
	INT64(8, long.class), 
	UINT64(8, BigInteger.class), 
	FLOAT32(4, float.class), 
	FLOAT64(8, double.class), 
	STRING(1, String.class), 
	TIME(8, long.class), 
	DURATION(8, long.class);

	private final int length;
	private final Class<?> javaClass;
	
	DataType(int length, Class<?> javaClass) {
		this.length = length;
		this.javaClass = javaClass;
	}

	public int getLength() {
		return length;
	}

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public String toString() {

		// Get string
		String string = this.name().toLowerCase();

		return string;

	}

}
