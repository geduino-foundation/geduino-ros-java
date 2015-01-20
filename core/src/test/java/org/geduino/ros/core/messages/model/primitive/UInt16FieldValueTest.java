package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class UInt16FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint16 field value
		UInt16FieldValue uInt16FieldValue = new UInt16FieldValue();
		uInt16FieldValue.setIntegerValue(30789);

		// Assert
		assertBytes(new byte[] { 69, 120 }, uInt16FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create uint16 field value
		UInt16FieldValue uInt16FieldValue = new UInt16FieldValue();

		// Set bytes
		uInt16FieldValue.setBytes(new byte[] { 69, 120 });

		// Assert
		assertEquals(30789, uInt16FieldValue.getIntegerValue());

	}

}
