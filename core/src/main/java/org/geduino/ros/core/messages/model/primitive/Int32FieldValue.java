package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;

public class Int32FieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private int intValue;

	public Int32FieldValue() {
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
		byte[] bytes = new byte[4];
		bytes[0] = (byte) intValue;
		bytes[1] = (byte) (intValue >> 8);
		bytes[2] = (byte) (intValue >> 16);
		bytes[3] = (byte) (intValue >> 24);

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 4) {

			// Get int value value
			intValue = ((bytes[3] << 24) & 0xFF000000)
					| ((bytes[2] << 16) & 0x00FF0000)
					| ((bytes[1] << 8) & 0x0000FF00) | (bytes[0] & 0x000000FF);

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
		Int32FieldValue other = (Int32FieldValue) obj;
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
