package org.geduino.ros.tcpros;

import java.math.BigInteger;

class PrimitiveBytes {

	static final byte[] BOOL_FALSE_BYTES = new byte[] { 0 };

	static final byte[] BOOL_TRUE_BYTES = new byte[] { 1 };

	static final float FLOAT_32_POSITIVE = 3.1415f;

	static final byte[] FLOAT_32_POSITIVE_BYTES = new byte[] { 86, 14, 73, 64 };

	static final float FLOAT_32_NEGATIVE = -3.1415f;

	static final byte[] FLOAT_32_NEGATIVE_BYTES = new byte[] { 86, 14, 73, -64 };

	static final double FLOAT_64_POSITIVE = 3.1415d;

	static final byte[] FLOAT_64_POSITIVE_BYTES = new byte[] { 111, 18, -125,
			-64, -54, 33, 9, 64 };

	static final double FLOAT_64_NEGATIVE = -3.1415d;

	static final byte[] FLOAT_64_NEGATIVE_BYTES = new byte[] { 111, 18, -125,
			-64, -54, 33, 9, -64 };

	static final int INT_8_POSITIVE = 32;

	static final byte[] INT_8_POSITIVE_BYTES = new byte[] { 32 };

	static final int INT_8_NEGATIVE = -32;

	static final byte[] INT_8_NEGATIVE_BYTES = new byte[] { -32 };

	static final int INT_16_POSITIVE = 12789;

	static final byte[] INT_16_POSITIVE_BYTES = new byte[] { -11, 49 };

	static final int INT_16_NEGATIVE = -12789;

	static final byte[] INT_16_NEGATIVE_BYTES = new byte[] { 11, -50 };

	static final int INT_32_POSITIVE = 1147483647;

	static final byte[] INT_32_POSITIVE_BYTES = new byte[] { -1, 53, 101, 68 };

	static final int INT_32_NEGATIVE = -1147483647;

	static final byte[] INT_32_NEGATIVE_BYTES = new byte[] { 1, -54, -102, -69 };

	static final long INT_64_POSITIVE = 31474836477673L;

	static final byte[] INT_64_POSITIVE_BYTES = new byte[] { -23, -106, 114,
			78, -96, 28, 0, 0 };

	static final long INT_64_NEGATIVE = -31474836477673L;

	static final byte[] INT_64_NEGATIVE_BYTES = new byte[] { 23, 105, -115,
			-79, 95, -29, -1, -1 };

	static final int UINT_8 = 172;

	static final byte[] UINT_8_BYTES = new byte[] { -84 };

	static final int UINT_16 = 30789;

	static final byte[] UINT_16_BYTES = new byte[] { 69, 120 };

	static final long UINT_32 = 3147483647L;

	static final byte[] UINT_32_BYTES = new byte[] { -1, -55, -102, -69 };

	static final BigInteger UINT_64 = new BigInteger(new byte[] { 28, -96, 78,
			114, -106, -23 });

	static final byte[] UINT_64_BYTES = new byte[] { -23, -106, 114, 78, -96,
			28, 0, 0 };

	static final String STRING = "test";

	static final byte[] STRING_BYTES = STRING.getBytes();

}
