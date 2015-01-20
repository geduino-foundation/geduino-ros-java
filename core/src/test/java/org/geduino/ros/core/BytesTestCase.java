package org.geduino.ros.core;

import java.util.Arrays;

import junit.framework.TestCase;

public abstract class BytesTestCase extends TestCase {

	protected void assertBytes(byte[] expectedBytes, byte[] actualBytes) {

		if (expectedBytes.length == actualBytes.length) {

			for (int index = 0; index < expectedBytes.length; index++) {

				if (expectedBytes[index] != actualBytes[index]) {

					// Fail
					fail("expected: " + Arrays.toString(expectedBytes) + " but was: "
							+ Arrays.toString(actualBytes));

				}

			}

		} else {

			// Fail
			fail("expected: " + expectedBytes.length + " bytes but was: "
					+ actualBytes.length);

		}

	}

}
