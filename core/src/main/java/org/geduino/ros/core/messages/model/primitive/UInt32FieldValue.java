package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;

public class UInt32FieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private long longValue;

	public UInt32FieldValue() {
		this.longValue = 0;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	@Override
	public byte[] getBytes() {

		// Get bytes
		byte[] bytes = new byte[4];
		bytes[0] = (byte) longValue;
		bytes[1] = (byte) (longValue >> 8);
		bytes[2] = (byte) (longValue >> 16);
		bytes[3] = (byte) (longValue >> 24);

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 4) {

			// Get long value
			longValue = 0xFF & bytes[3];
			longValue <<= 8;
			longValue += 0xFF & bytes[2];
			longValue <<= 8;
			longValue += 0xFF & bytes[1];
			longValue <<= 8;
			longValue += 0xFF & bytes[0];

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
		result = prime * result + (int) (longValue ^ (longValue >>> 32));
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
		UInt32FieldValue other = (UInt32FieldValue) obj;
		if (longValue != other.longValue)
			return false;
		return true;
	}

	@Override
	public String toString() {

		// Get string
		String string = Long.toString(longValue);

		return string;

	}

}
