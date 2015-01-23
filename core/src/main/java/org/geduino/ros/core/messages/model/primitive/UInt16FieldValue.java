package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;

public class UInt16FieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private int intValue;

	public UInt16FieldValue() {
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
		byte[] bytes = new byte[2];
		bytes[0] = (byte) intValue;
		bytes[1] = (byte) (intValue >> 8);

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 2) {

			// Get int value value
			intValue = 0xFF & bytes[1];
			intValue <<= 8;
			intValue += 0xFF & bytes[0];

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
		UInt16FieldValue other = (UInt16FieldValue) obj;
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
