package org.geduino.ros.tcpros;

import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;

import org.geduino.ros.core.transport.exception.RosTransportSerializationException;

public class OuputStreamPrimitiveTypeWriterTest extends TestCase {

	private ByteBufferOutputStream byteBufferOutputStream;
	private OutputStreamPrimitiveTypeWriter outputStreamPrimitiveTypeWriter;

	@Override
	public void setUp() {

		// Create byte buffer ouput stream
		byteBufferOutputStream = new ByteBufferOutputStream(64);

		// Create primitive type ouput stream writer
		outputStreamPrimitiveTypeWriter = new OutputStreamPrimitiveTypeWriter(
				byteBufferOutputStream);

	}

	public void testBool() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter.writeBool(true);

		// Assert
		assertEquals(PrimitiveBytes.BOOL_TRUE_BYTES,
				byteBufferOutputStream.reset());

		// Write
		outputStreamPrimitiveTypeWriter.writeBool(false);

		// Assert
		assertEquals(PrimitiveBytes.BOOL_FALSE_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testFloat32() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter
				.writeFloat32(PrimitiveBytes.FLOAT_32_POSITIVE);

		// Assert
		assertEquals(PrimitiveBytes.FLOAT_32_POSITIVE_BYTES,
				byteBufferOutputStream.reset());

		// Write
		outputStreamPrimitiveTypeWriter
				.writeFloat32(PrimitiveBytes.FLOAT_32_NEGATIVE);

		// Assert
		assertEquals(PrimitiveBytes.FLOAT_32_NEGATIVE_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testFloat64() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter
				.writeFloat64(PrimitiveBytes.FLOAT_64_POSITIVE);

		// Assert
		assertEquals(PrimitiveBytes.FLOAT_64_POSITIVE_BYTES,
				byteBufferOutputStream.reset());

		// Write
		outputStreamPrimitiveTypeWriter
				.writeFloat64(PrimitiveBytes.FLOAT_64_NEGATIVE);

		// Assert
		assertEquals(PrimitiveBytes.FLOAT_64_NEGATIVE_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testInt8() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter
				.writeInt8(PrimitiveBytes.INT_8_POSITIVE);

		// Assert
		assertEquals(PrimitiveBytes.INT_8_POSITIVE_BYTES,
				byteBufferOutputStream.reset());

		// Write
		outputStreamPrimitiveTypeWriter
				.writeInt8(PrimitiveBytes.INT_8_NEGATIVE);

		// Assert
		assertEquals(PrimitiveBytes.INT_8_NEGATIVE_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testInt16() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter
				.writeInt16(PrimitiveBytes.INT_16_POSITIVE);

		// Assert
		assertEquals(PrimitiveBytes.INT_16_POSITIVE_BYTES,
				byteBufferOutputStream.reset());

		// Write
		outputStreamPrimitiveTypeWriter
				.writeInt16(PrimitiveBytes.INT_16_NEGATIVE);

		// Assert
		assertEquals(PrimitiveBytes.INT_16_NEGATIVE_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testInt32() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter
				.writeInt32(PrimitiveBytes.INT_32_POSITIVE);

		// Assert
		assertEquals(PrimitiveBytes.INT_32_POSITIVE_BYTES,
				byteBufferOutputStream.reset());

		// Write
		outputStreamPrimitiveTypeWriter
				.writeInt32(PrimitiveBytes.INT_32_NEGATIVE);

		// Assert
		assertEquals(PrimitiveBytes.INT_32_NEGATIVE_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testInt64() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter
				.writeInt64(PrimitiveBytes.INT_64_POSITIVE);

		// Assert
		assertEquals(PrimitiveBytes.INT_64_POSITIVE_BYTES,
				byteBufferOutputStream.reset());

		// Write
		outputStreamPrimitiveTypeWriter
				.writeInt64(PrimitiveBytes.INT_64_NEGATIVE);

		// Assert
		assertEquals(PrimitiveBytes.INT_64_NEGATIVE_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testUInt8() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter.writeUInt8(PrimitiveBytes.UINT_8);

		// Assert
		assertEquals(PrimitiveBytes.UINT_8_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testUInt16() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter.writeUInt16(PrimitiveBytes.UINT_16);

		// Assert
		assertEquals(PrimitiveBytes.UINT_16_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testUInt32() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter.writeUInt32(PrimitiveBytes.UINT_32);

		// Assert
		assertEquals(PrimitiveBytes.UINT_32_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testUInt64() throws RosTransportSerializationException, IOException {

		// Write
		outputStreamPrimitiveTypeWriter.writeUInt64(PrimitiveBytes.UINT_64);

		// Assert
		assertEquals(PrimitiveBytes.UINT_64_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testString() throws RosTransportSerializationException,
			IOException {

		// Write
		outputStreamPrimitiveTypeWriter.writeString(PrimitiveBytes.STRING);

		// Assert
		assertEquals(PrimitiveBytes.STRING_BYTES,
				byteBufferOutputStream.reset());

	}

	public void testTime() {
		// TODO
	}

	public void testDuration() {
		// TODO
	}

	private void assertEquals(byte[] expectedBytes, byte[] actualBytes) {

		if (expectedBytes.length == actualBytes.length) {

			for (int index = 0; index < expectedBytes.length; index++) {

				if (expectedBytes[index] != actualBytes[index]) {

					// Fail
					fail("expected: " + Arrays.toString(expectedBytes)
							+ " but was: " + Arrays.toString(actualBytes));

				}

			}

		} else {

			// Fail
			fail("expected: " + expectedBytes.length + " bytes but was: "
					+ actualBytes.length);

		}

	}

}
