package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class UInt32FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint16 field value
		UInt32FieldValue uInt32FieldValue = new UInt32FieldValue();
		uInt32FieldValue.setLongValue(3147483647L);

		// Assert
		assertBytes(new byte[] { -1, -55, -102, -69 }, uInt32FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create uint16 field value
		UInt32FieldValue uInt32FieldValue = new UInt32FieldValue();

		// Set bytes
		uInt32FieldValue.setBytes(new byte[] { -1, -55, -102, -69 });

		// Assert
		assertEquals(3147483647L, uInt32FieldValue.getLongValue());

	}

}
