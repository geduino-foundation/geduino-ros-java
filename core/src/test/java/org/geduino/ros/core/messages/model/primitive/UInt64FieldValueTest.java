package org.geduino.ros.core.messages.model.primitive;

import java.math.BigInteger;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public class UInt64FieldValueTest extends FieldValueTestCase {

	public void testGetBytes() {

		// Create uint64 field value
		UInt64FieldValue uInt64FieldValue = new UInt64FieldValue();
		uInt64FieldValue.setBigIntegerValue(new BigInteger(new byte[] { 28,
				-96, 78, 114, -106, -23 }));

		// Assert
		assertBytes(new byte[] { -23, -106, 114, 78, -96, 28, 0, 0 },
				uInt64FieldValue);

	}

	public void testSetBytes() throws RosMessageSerializeException {

		// Create int64 field value
		UInt64FieldValue uInt64FieldValue = new UInt64FieldValue();

		// Set bytes
		uInt64FieldValue.setBytes(new byte[] { -23, -106, 114, 78, -96, 28, 0,
				0 });

		// Assert
		assertEquals(
				new BigInteger(new byte[] { 28, -96, 78, 114, -106, -23 }),
				uInt64FieldValue.getBigIntegerValue());

	}

}
