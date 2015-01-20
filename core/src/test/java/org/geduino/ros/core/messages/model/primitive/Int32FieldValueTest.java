package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class Int32FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint16 field value
		Int32FieldValue int32FieldValue = new Int32FieldValue();
		int32FieldValue.setIntegerValue(1147483647);

		// Assert
		assertBytes(new byte[] { -1, 53, 101, 68 }, int32FieldValue);

	}

	public void testGetBytesNegative() {

		// Create uint16 field value
		Int32FieldValue int32FieldValue = new Int32FieldValue();
		int32FieldValue.setIntegerValue(-1147483647);

		// Assert
		assertBytes(new byte[] { 1, -54, -102, -69 }, int32FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create uint16 field value
		Int32FieldValue int32FieldValue = new Int32FieldValue();

		// Set bytes
		int32FieldValue.setBytes(new byte[] { -1, 53, 101, 68 });

		// Assert
		assertEquals(1147483647, int32FieldValue.getIntegerValue());

	}

	public void testSetBytesNegative() throws RosMessageSerializeException {

		// Create uint16 field value
		Int32FieldValue int32FieldValue = new Int32FieldValue();

		// Set bytes
		int32FieldValue.setBytes(new byte[] { 1, -54, -102, -69 });

		// Assert
		assertEquals(-1147483647, int32FieldValue.getIntegerValue());

	}

}
