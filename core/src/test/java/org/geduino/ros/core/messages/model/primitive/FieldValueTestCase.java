package org.geduino.ros.core.messages.model.primitive;

import junit.framework.TestCase;

import org.geduino.ros.core.messages.model.FieldValue;

public abstract class FieldValueTestCase extends TestCase {

	void assertBytes(byte[] expectedBytes, FieldValue actual) {

		// Get field value bytes
		byte[] actualBytes = actual.getBytes();

		if (expectedBytes.length == actualBytes.length) {

			for (int index = 0; index < expectedBytes.length; index++) {

				if (expectedBytes[index] != actualBytes[index]) {

					// Fail
					fail("expected byte at index " + index + ": "
							+ expectedBytes[index] + " but was: "
							+ actualBytes.length);

				}

			}

		} else {

			// Fail
			fail("expected: " + expectedBytes.length + " bytes but was: "
					+ actualBytes.length);

		}

	}

}
