package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class Int64FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint64 field value
		Int64FieldValue int64FieldValue = new Int64FieldValue();
		int64FieldValue.setLongValue(31474836477673L);

		// Assert
		assertBytes(new byte[] { -23, -106, 114, 78, -96, 28, 0, 0 },
				int64FieldValue);

	}

	public void testGetBytesNegative() {

		// Create int64 field value
		Int64FieldValue int64FieldValue = new Int64FieldValue();
		int64FieldValue.setLongValue(-31474836477673L);

		// Assert
		assertBytes(new byte[] { 23, 105, -115, -79, 95, -29, -1, -1 },
				int64FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create int64 field value
		Int64FieldValue int64FieldValue = new Int64FieldValue();

		// Set bytes
		int64FieldValue
				.setBytes(new byte[] { -23, -106, 114, 78, -96, 28, 0, 0 });

		// Assert
		assertEquals(31474836477673L, int64FieldValue.getLongValue());

	}

	public void testSetBytesNegative() throws RosMessageSerializeException {

		// Create int64 field value
		Int64FieldValue int64FieldValue = new Int64FieldValue();

		// Set bytes
		int64FieldValue.setBytes(new byte[] { 23, 105, -115, -79, 95, -29, -1,
				-1 });

		// Assert
		assertEquals(-31474836477673L, int64FieldValue.getLongValue());

	}

}
