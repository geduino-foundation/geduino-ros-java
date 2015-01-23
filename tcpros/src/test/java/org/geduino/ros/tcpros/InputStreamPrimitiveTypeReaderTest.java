package org.geduino.ros.tcpros;

import java.io.IOException;

import junit.framework.TestCase;

import org.geduino.ros.core.transport.exception.RosTransportSerializationException;

public class InputStreamPrimitiveTypeReaderTest extends TestCase {

	private ByteBufferInputStream byteBufferInputStream;
	private InputStreamPrimitiveTypeReader inputStreamPrimitiveTypeReader;

	@Override
	public void setUp() {

		// Create byte buffer input stream
		byteBufferInputStream = new ByteBufferInputStream();

		// Create primitive type input stream reader
		inputStreamPrimitiveTypeReader = new InputStreamPrimitiveTypeReader(
				byteBufferInputStream);

	}

	public void testBool() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.BOOL_TRUE_BYTES);

		// Assert
		assertTrue(inputStreamPrimitiveTypeReader.readBool());

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.BOOL_FALSE_BYTES);

		// Assert
		assertFalse(inputStreamPrimitiveTypeReader.readBool());

	}

	public void testFloat32() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.FLOAT_32_POSITIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.FLOAT_32_POSITIVE,
				inputStreamPrimitiveTypeReader.readFloat32());

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.FLOAT_32_NEGATIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.FLOAT_32_NEGATIVE,
				inputStreamPrimitiveTypeReader.readFloat32());

	}

	public void testFloat64() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.FLOAT_64_POSITIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.FLOAT_64_POSITIVE,
				inputStreamPrimitiveTypeReader.readFloat64());

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.FLOAT_64_NEGATIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.FLOAT_64_NEGATIVE,
				inputStreamPrimitiveTypeReader.readFloat64());

	}

	public void testInt8() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.INT_8_POSITIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.INT_8_POSITIVE,
				inputStreamPrimitiveTypeReader.readInt8());

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.INT_8_NEGATIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.INT_8_NEGATIVE,
				inputStreamPrimitiveTypeReader.readInt8());

	}

	public void testInt16() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.INT_16_POSITIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.INT_16_POSITIVE,
				inputStreamPrimitiveTypeReader.readInt16());

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.INT_16_NEGATIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.INT_16_NEGATIVE,
				inputStreamPrimitiveTypeReader.readInt16());

	}

	public void testInt32() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.INT_32_POSITIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.INT_32_POSITIVE,
				inputStreamPrimitiveTypeReader.readInt32());

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.INT_32_NEGATIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.INT_32_NEGATIVE,
				inputStreamPrimitiveTypeReader.readInt32());

	}

	public void testInt64() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.INT_64_POSITIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.INT_64_POSITIVE,
				inputStreamPrimitiveTypeReader.readInt64());

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.INT_64_NEGATIVE_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.INT_64_NEGATIVE,
				inputStreamPrimitiveTypeReader.readInt64());

	}

	public void testUInt8() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.UINT_8_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.UINT_8,
				inputStreamPrimitiveTypeReader.readUInt8());

	}

	public void testUInt16() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.UINT_16_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.UINT_16,
				inputStreamPrimitiveTypeReader.readUInt16());

	}

	public void testUInt32() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.UINT_32_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.UINT_32,
				inputStreamPrimitiveTypeReader.readUInt32());

	}

	public void testUInt64() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.UINT_64_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.UINT_64,
				inputStreamPrimitiveTypeReader.readUInt64());

	}

	public void testString() throws RosTransportSerializationException,
			IOException {

		// Reset buffer
		byteBufferInputStream.reset(PrimitiveBytes.STRING_BYTES);

		// Assert
		assertEquals(PrimitiveBytes.STRING,
				inputStreamPrimitiveTypeReader.readString(PrimitiveBytes.STRING
						.length()));

	}

	public void testTime() {
		// TODO
	}

	public void testDuration() {
		// TODO
	}

}
