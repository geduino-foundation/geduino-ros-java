package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class Float32FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint64 field value
		Float32FieldValue float32FieldValue = new Float32FieldValue();
		float32FieldValue.setFloatValue(3.1415f);

		// Assert
		assertBytes(new byte[] { 86, 14, 73, 64 }, float32FieldValue);

	}

	public void testGetBytesNegative() {

		// Create uint64 field value
		Float32FieldValue float32FieldValue = new Float32FieldValue();
		float32FieldValue.setFloatValue(-3.1415f);

		// Assert
		assertBytes(new byte[] { 86, 14, 73, -64 }, float32FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create int64 field value
		Float32FieldValue float32FieldValue = new Float32FieldValue();

		// Set bytes
		float32FieldValue.setBytes(new byte[] { 86, 14, 73, 64 });

		// Assert
		assertEquals(3.1415f, float32FieldValue.getFloatValue());

	}

	public void testSetBytesNegative() throws RosMessageSerializeException {

		// Create int64 field value
		Float32FieldValue float32FieldValue = new Float32FieldValue();

		// Set bytes
		float32FieldValue.setBytes(new byte[] { 86, 14, 73, -64 });

		// Assert
		assertEquals(-3.1415f, float32FieldValue.getFloatValue());

	}

}
