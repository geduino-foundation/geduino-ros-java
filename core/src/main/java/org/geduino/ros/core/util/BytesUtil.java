package org.geduino.ros.core.util;

public class BytesUtil {

	public static byte[] reverse(byte[] bytes) {
	
		// Get byte array length
		int length = bytes.length;
		
		// Create reverse array
		byte[] reverse = new byte[length];
		int offset = length - 1;
		
		for (int index = 0; index < length; index++) {
			reverse[index] = bytes[offset - index];
		}
		
		return reverse;
		
	}
	
	public static byte[] toLittleEndianBytes(int integer) {

		// Get bytes
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (integer & 0xFF);
		bytes[1] = (byte) ((integer >> 8) & 0xFF);
		bytes[2] = (byte) ((integer >> 16) & 0xFF);
		bytes[3] = (byte) ((integer >> 24) & 0xFF);

		return bytes;

	}

	public static int toLittleEndianInt(byte[] bytes) {

		// Get int value
		int integer = 0xFF & bytes[3];
		integer <<= 8;
		integer += 0xFF & bytes[2];
		integer <<= 8;
		integer += 0xFF & bytes[1];
		integer <<= 8;
		integer += 0xFF & bytes[0];

		return integer;

	}
	
}
