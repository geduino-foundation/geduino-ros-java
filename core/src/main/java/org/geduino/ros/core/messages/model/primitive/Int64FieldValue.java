package org.geduino.ros.core.messages.model.primitive;

import java.nio.ByteBuffer;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;
import org.geduino.ros.core.util.BytesUtil;

public class Int64FieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private long longValue;

	public Int64FieldValue() {
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

		byte[] bytes = new byte[8];
		
		// Create byte buffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(Long.SIZE);
		byteBuffer.putLong(longValue);
		byteBuffer.flip();
		
		// Get bytes
		byteBuffer.get(bytes);
		
		// Reverse bytes
		byte[] reversedBytes = BytesUtil.reverse(bytes);
		
		return reversedBytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 8) {

			// Create byte buffer
			ByteBuffer byteBuffer = ByteBuffer.allocate(Long.SIZE);
			byteBuffer.put(bytes[7]);
			byteBuffer.put(bytes[6]);
			byteBuffer.put(bytes[5]);
			byteBuffer.put(bytes[4]);
			byteBuffer.put(bytes[3]);
			byteBuffer.put(bytes[2]);
			byteBuffer.put(bytes[1]);
			byteBuffer.put(bytes[0]);
			byteBuffer.flip();

			// Get long value
			longValue = byteBuffer.getLong();

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
		Int64FieldValue other = (Int64FieldValue) obj;
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
