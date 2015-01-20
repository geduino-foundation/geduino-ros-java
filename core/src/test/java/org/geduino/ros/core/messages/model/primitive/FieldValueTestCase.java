package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.BytesTestCase;
import org.geduino.ros.core.messages.model.FieldValue;

public abstract class FieldValueTestCase extends BytesTestCase {

	protected void assertBytes(byte[] expectedBytes, FieldValue actual) {

		// Get field value bytes
		byte[] actualBytes = actual.getBytes();

		// Assert
		assertBytes(expectedBytes, actualBytes);

	}

}
