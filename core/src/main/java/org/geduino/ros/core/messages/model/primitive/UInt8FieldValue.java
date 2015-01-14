package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;

public class UInt8FieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private int intValue;

	public UInt8FieldValue() {
		this.intValue = 0;
	}

	public int getIntegerValue() {
		return intValue;
	}

	public void setIntegerValue(int intValue) {
		this.intValue = intValue;
	}

	@Override
	public byte[] getBytes() {

		// Get bytes
		byte[] bytes = new byte[1];
		bytes[0] = (byte) intValue;

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 1) {

			// Get int value value
			intValue = (int) bytes[0];

		} else {

			// Throw exception
			throw new RosMessageSerializeException(
					"unexpected byte array length: " + bytes.length);

		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intValue;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UInt8FieldValue other = (UInt8FieldValue) obj;
		if (intValue != other.intValue)
			return false;
		return true;
	}

	@Override
	public String toString() {

		// Get string
		String string = Integer.toString(intValue);

		return string;

	}

}
