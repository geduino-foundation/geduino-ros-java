package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;

public class BoolFieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private boolean booleanValue;

	public BoolFieldValue() {
		booleanValue = false;
	}

	public boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	@Override
	public byte[] getBytes() {

		byte[] bytes = new byte[1];

		if (booleanValue) {
			bytes[0] = 0x01;
		} else {
			bytes[0] = 0x00;
		}

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 1) {

			// Get boolean value
			booleanValue = bytes[0] == 0x01;

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
		result = prime * result + (booleanValue ? 1231 : 1237);
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
		BoolFieldValue other = (BoolFieldValue) obj;
		if (booleanValue != other.booleanValue)
			return false;
		return true;
	}

	@Override
	public String toString() {

		// Get string
		String string = Boolean.toString(booleanValue);

		return string;

	}

}
