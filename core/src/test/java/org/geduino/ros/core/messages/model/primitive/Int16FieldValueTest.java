package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class Int16FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint16 field value
		Int16FieldValue int16FieldValue = new Int16FieldValue();
		int16FieldValue.setIntegerValue(12789);

		// Assert
		assertBytes(new byte[] { -11, 49 }, int16FieldValue);

	}

	public void testGetBytesNegative() {

		// Create uint16 field value
		Int16FieldValue int16FieldValue = new Int16FieldValue();
		int16FieldValue.setIntegerValue(-12789);

		// Assert
		assertBytes(new byte[] { 11, -50 }, int16FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create uint16 field value
		Int16FieldValue int16FieldValue = new Int16FieldValue();

		// Set bytes
		int16FieldValue.setBytes(new byte[] { -11, 49 });

		// Assert
		assertEquals(12789, int16FieldValue.getIntegerValue());

	}

	public void testSetBytesNegative() throws RosMessageSerializeException {

		// Create uint16 field value
		Int16FieldValue int16FieldValue = new Int16FieldValue();

		// Set bytes
		int16FieldValue.setBytes(new byte[] { 11, -50 });

		// Assert
		assertEquals(-12789, int16FieldValue.getIntegerValue());

	}

}
