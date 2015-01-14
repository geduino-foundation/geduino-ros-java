package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class UInt8FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint8 field value
		UInt8FieldValue uInt8FieldValue = new UInt8FieldValue();
		uInt8FieldValue.setIntegerValue(32);

		// Assert
		assertBytes(new byte[] { 0x20 }, uInt8FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create uint8 field value
		UInt8FieldValue uInt8FieldValue = new UInt8FieldValue();

		// Set bytes
		uInt8FieldValue.setBytes(new byte[] { 0x20 });

		// Assert
		assertEquals(32, uInt8FieldValue.getIntegerValue());

	}

}
