package org.geduino.ros.core.messages.model.primitive;

import java.nio.ByteBuffer;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;
import org.geduino.ros.core.util.BytesUtil;

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
		bytes[0] = (byte) (bits & 0xff);
		bytes[1] = (byte) ((bits >> 8) & 0xff);
		bytes[2] = (byte) ((bits >> 16) & 0xff);
		bytes[3] = (byte) ((bits >> 24) & 0xff);
		bytes[4] = (byte) ((bits >> 32) & 0xff);
		bytes[5] = (byte) ((bits >> 40) & 0xff);
		bytes[6] = (byte) ((bits >> 48) & 0xff);
		bytes[7] = (byte) ((bits >> 56) & 0xff);

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 8) {

			// Reverse bytes
			byte[] reverseBytes = BytesUtil.reverse(bytes);

			// Get double value
			doubleValue = ByteBuffer.wrap(reverseBytes).getDouble();

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
