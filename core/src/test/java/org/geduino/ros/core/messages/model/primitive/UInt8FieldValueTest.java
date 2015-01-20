package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class UInt8FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint8 field value
		UInt8FieldValue uInt8FieldValue = new UInt8FieldValue();
		uInt8FieldValue.setIntegerValue(172);

		// Assert
		assertBytes(new byte[] { -84 }, uInt8FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create uint8 field value
		UInt8FieldValue uInt8FieldValue = new UInt8FieldValue();

		// Set bytes
		uInt8FieldValue.setBytes(new byte[] { -84 });

		// Assert
		assertEquals(172, uInt8FieldValue.getIntegerValue());

	}

}
