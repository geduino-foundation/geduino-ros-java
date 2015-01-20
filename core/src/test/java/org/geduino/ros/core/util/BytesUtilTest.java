package org.geduino.ros.core.util;

import org.geduino.ros.core.BytesTestCase;

public class BytesUtilTest extends BytesTestCase {

	public void test() {

		// Assert
		assertBytes(new byte[] { 1, 2, 3, 4 },
				BytesUtil.reverse(new byte[] { 4, 3, 2, 1 }));

	}

	public void testSingleElementArray() {

		// Assert
		assertBytes(new byte[] { 1 }, BytesUtil.reverse(new byte[] { 1 }));

	}

	public void testEmptyArray() {

		// Assert
		assertBytes(new byte[] {}, BytesUtil.reverse(new byte[] {}));

	}

	public void testToLittleEndianBytes() {

		// Assert
		assertBytes(new byte[] { 14, 22, 0, 0 },
				BytesUtil.toLittleEndianBytes(5646));

	}

	public void testToLittleEndianInt() {

		// Assert
		assertEquals(5646,
				BytesUtil.toLittleEndianInt(new byte[] { 14, 22, 0, 0 }));

	}

}
