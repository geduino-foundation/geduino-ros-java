package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;

public class Float32FieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private float floatValue;

	public Float32FieldValue() {
		this.floatValue = 0;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	@Override
	public byte[] getBytes() {

		// Get bit
		int bits = Float.floatToIntBits(floatValue);

		// Get bytes
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (bits & 0xff);
		bytes[1] = (byte) ((bits >> 8) & 0xff);
		bytes[2] = (byte) ((bits >> 16) & 0xff);
		bytes[3] = (byte) ((bits >> 24) & 0xff);

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 4) {

			// Get bits
			int bits = ((bytes[3] << 24) & 0xFF000000)
					| ((bytes[2] << 16) & 0x00FF0000)
					| ((bytes[1] << 8) & 0x0000FF00) | (bytes[0] & 0x000000FF);

			// Get float value
			floatValue = Float.intBitsToFloat(bits);

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
		result = prime * result + Float.floatToIntBits(floatValue);
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
		Float32FieldValue other = (Float32FieldValue) obj;
		if (Float.floatToIntBits(floatValue) != Float
				.floatToIntBits(other.floatValue))
			return false;
		return true;
	}

	@Override
	public String toString() {

		// Get string
		String string = Float.toString(floatValue);

		return string;

	}

}
