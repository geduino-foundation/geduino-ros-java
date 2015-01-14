package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class BoolFieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create bool field value
		BoolFieldValue boolFieldValue = new BoolFieldValue();

		// Assert
		assertBytes(new byte[] { 0x00 }, boolFieldValue);

		// Set value to true
		boolFieldValue.setBooleanValue(true);

		// Assert
		assertBytes(new byte[] { 0x01 }, boolFieldValue);
		
	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create bool field value
		BoolFieldValue boolFieldValue = new BoolFieldValue();

		// Set bytes
		boolFieldValue.setBytes(new byte[] { 0x00 });

		// Assert
		assertFalse(boolFieldValue.getBooleanValue());

		// Set bytes
		boolFieldValue.setBytes(new byte[] { 0x01 });

		// Assert
		assertTrue(boolFieldValue.getBooleanValue());

	}

}
