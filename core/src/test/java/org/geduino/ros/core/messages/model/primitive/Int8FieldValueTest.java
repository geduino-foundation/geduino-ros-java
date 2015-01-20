package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class Int8FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create int8 field value
		Int8FieldValue int8FieldValue = new Int8FieldValue();
		int8FieldValue.setIntegerValue(32);

		// Assert
		assertBytes(new byte[] { 32 }, int8FieldValue);

	}

	public void testGetBytesNegative() {

		// Create int8 field value
		Int8FieldValue int8FieldValue = new Int8FieldValue();
		int8FieldValue.setIntegerValue(-32);

		// Assert
		assertBytes(new byte[] { -32 }, int8FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create int8 field value
		Int8FieldValue int8FieldValue = new Int8FieldValue();

		// Set bytes
		int8FieldValue.setBytes(new byte[] { 32 });

		// Assert
		assertEquals(32, int8FieldValue.getIntegerValue());

	}

	public void testSetBytesNegative() throws RosMessageSerializeException {

		// Create int8 field value
		Int8FieldValue int8FieldValue = new Int8FieldValue();
		int8FieldValue.setBytes(new byte[] { -32 });

		// Assert
		assertEquals(-32, int8FieldValue.getIntegerValue());

	}

}
