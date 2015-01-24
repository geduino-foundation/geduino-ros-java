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
	
}
