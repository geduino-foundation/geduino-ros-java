package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class Float64FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create float64 field value
		Float64FieldValue float64FieldValue = new Float64FieldValue();
		float64FieldValue.setDoubleValue(3.1415d);

		// Assert
		assertBytes(new byte[] { 111, 18, -125, -64, -54, 33, 9, 64 }, float64FieldValue);

	}

	public void testGetBytesNegative() {

		// Create uint64 field value
		Float64FieldValue float64FieldValue = new Float64FieldValue();
		float64FieldValue.setDoubleValue(-3.1415d);

		// Assert
		assertBytes(new byte[] { 111, 18, -125, -64, -54, 33, 9, -64  }, float64FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create int64 field value
		Float64FieldValue float64FieldValue = new Float64FieldValue();

		// Set bytes
		float64FieldValue.setBytes(new byte[] { 111, 18, -125, -64, -54, 33, 9, 64  });

		// Assert
		assertEquals(3.1415d, float64FieldValue.getDoubleValue());

	}

	public void testSetBytesNegative() throws RosMessageSerializeException {

		// Create int64 field value
		Float64FieldValue float64FieldValue = new Float64FieldValue();

		// Set bytes
		float64FieldValue.setBytes(new byte[] { 111, 18, -125, -64, -54, 33, 9, -64  });

		// Assert
		assertEquals(-3.1415d, float64FieldValue.getDoubleValue());

	}

}
