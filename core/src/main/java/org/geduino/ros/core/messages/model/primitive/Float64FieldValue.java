package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;

public class Float64FieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private double doubleValue;

	public Float64FieldValue() {
		this.doubleValue = 0;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	@Override
	public byte[] getBytes() {

		// Get bit
		long bits = Double.doubleToLongBits(doubleValue);

		// Get bytes
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (bits);
		bytes[1] = (byte) (bits >> 8);
		bytes[2] = (byte) (bits >> 16);
		bytes[3] = (byte) (bits >> 24);
		bytes[4] = (byte) (bits >> 32);
		bytes[5] = (byte) (bits >> 40);
		bytes[6] = (byte) (bits >> 48);
		bytes[7] = (byte) (bits >> 56);

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 8) {

			// Get bits
			long bits = 0xFF & bytes[7];
			bits <<= 8;
			bits += 0xFF & bytes[6];
			bits <<= 8;
			bits += 0xFF & bytes[5];
			bits <<= 8;
			bits += 0xFF & bytes[4];
			bits <<= 8;
			bits += 0xFF & bytes[3];
			bits <<= 8;
			bits += 0xFF & bytes[2];
			bits <<= 8;
			bits += 0xFF & bytes[1];
			bits <<= 8;
			bits += 0xFF & bytes[0];

			// Get double value
			doubleValue = Double.longBitsToDouble(bits);

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
		long temp;
		temp = Double.doubleToLongBits(doubleValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Float64FieldValue other = (Float64FieldValue) obj;
		if (Double.doubleToLongBits(doubleValue) != Double
				.doubleToLongBits(other.doubleValue))
			return false;
		return true;
	}

	@Override
	public String toString() {

		// Get string
		String string = Double.toString(doubleValue);

		return string;

	}

}
