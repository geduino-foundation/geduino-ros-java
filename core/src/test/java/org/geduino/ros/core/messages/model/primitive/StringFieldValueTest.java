package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class StringFieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create string field value
		StringFieldValue stringFieldValue = new StringFieldValue();
		stringFieldValue.setStringValue("test");

		// Assert
		assertBytes("test".getBytes(), stringFieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create string field value
		StringFieldValue stringFieldValue = new StringFieldValue();

		// Set bytes
		stringFieldValue.setBytes("test".getBytes());

		// Assert
		assertEquals("test", stringFieldValue.getStringValue());

	}

}
