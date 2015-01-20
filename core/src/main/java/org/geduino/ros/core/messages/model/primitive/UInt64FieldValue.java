package org.geduino.ros.core.messages.model.primitive;

import java.math.BigInteger;
import java.util.Arrays;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;
import org.geduino.ros.core.util.BytesUtil;

public class UInt64FieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private BigInteger bigIntegerValue;

	public UInt64FieldValue() {
		this.bigIntegerValue = new BigInteger(new byte[] { 0 });
	}

	public BigInteger getBigIntegerValue() {
		return bigIntegerValue;
	}

	public void setBigIntegerValue(BigInteger bigIntegerValue) {
		this.bigIntegerValue = bigIntegerValue;
	}

	@Override
	public byte[] getBytes() {

		// Get big integer bytes
		byte[] bigIntegerBytes = bigIntegerValue.toByteArray();

		// Fill a 8 byte array
		byte[] bytes8 = new byte[8];
		Arrays.fill(bytes8, (byte) 0);
		int offset = 8 - bigIntegerBytes.length;
		for (int index = 0; index < bigIntegerBytes.length; index++) {
			bytes8[offset + index] = bigIntegerBytes[index];
		}

		// Reverse bytes
		byte[] reversedBytes = BytesUtil.reverse(bytes8);

		return reversedBytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		if (bytes.length == 8) {

			// Reverse bytes
			byte[] reversedBytes = BytesUtil.reverse(bytes);

			// Get big integer value
			bigIntegerValue = new BigInteger(reversedBytes);

		} else {

			// Throw exception
			throw new RosMessageSerializeException(
					"unexpected byte array length: " + bytes.length);

		}

	}

	@Override
	public String toString() {

		// Get string
		String string = bigIntegerValue.toString();

		return string;

	}

}
