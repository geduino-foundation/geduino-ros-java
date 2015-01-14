package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class Int8FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create int8 field value
		Int8FieldValue int8FieldValue = new Int8FieldValue();
		int8FieldValue.setIntegerValue(32);

		// Assert
		assertBytes(new byte[] { 0x20 }, int8FieldValue);

		// Set negative value
		int8FieldValue.setIntegerValue(-32);

		// Assert
		assertBytes(new byte[] { -0x20 }, int8FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create int8 field value
		Int8FieldValue int8FieldValue = new Int8FieldValue();

		// Set bytes
		int8FieldValue.setBytes(new byte[] { 0x20 });

		// Assert
		assertEquals(32, int8FieldValue.getIntegerValue());

		// Set bytes
		int8FieldValue.setBytes(new byte[] { -0x20 });

		// Assert
		assertEquals(-32, int8FieldValue.getIntegerValue());

	}

}
