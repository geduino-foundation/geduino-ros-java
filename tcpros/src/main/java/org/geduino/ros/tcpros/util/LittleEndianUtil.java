package org.geduino.ros.tcpros.util;

public class LittleEndianUtil {

	public static byte[] toLittleEndianBytes(int integer) {

		byte[] bytes = new byte[4];

		bytes[0] = (byte) (integer & 0xFF);
		bytes[1] = (byte) ((integer >> 8) & 0xFF);
		bytes[2] = (byte) ((integer >> 16) & 0xFF);
		bytes[3] = (byte) ((integer >> 24) & 0xFF);

		return bytes;

	}

	public static int toLittleEndianInt(byte[] bytes) {

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
