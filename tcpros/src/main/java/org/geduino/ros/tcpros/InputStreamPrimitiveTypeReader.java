package org.geduino.ros.tcpros;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import org.geduino.ros.core.exception.NotYetImplementedException;
import org.geduino.ros.core.transport.exception.RosTransportSerializationException;
import org.geduino.ros.core.transport.model.PrimitiveTypeReader;
import org.geduino.ros.core.util.BytesUtil;

public class InputStreamPrimitiveTypeReader implements PrimitiveTypeReader {

	private final InputStream inputStream;

	public InputStreamPrimitiveTypeReader(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public boolean readBool() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(1);

		// Get boolean value
		boolean booleanValue = bytes[0] != 0x00;

		return booleanValue;

	}

	@Override
	public long readDuration() throws IOException,
			RosTransportSerializationException {
		throw new NotYetImplementedException();
	}

	@Override
	public float readFloat32() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(4);

		// Get bits
		int bits = 0xFF & bytes[3];
		bits <<= 8;
		bits += 0xFF & bytes[2];
		bits <<= 8;
		bits += 0xFF & bytes[1];
		bits <<= 8;
		bits += 0xFF & bytes[0];

		// Get float value
		float floatValue = Float.intBitsToFloat(bits);

		return floatValue;

	}

	@Override
	public double readFloat64() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(8);

		// Get bits
		long bits = 0xFF & bytes[7];
		bits <<= 8;
		bits += 0xFF & bytes[6];
		bits <<= 8;
		bits += 0xFF & bytes[5];
		bits <<= 8;
		bits += 0xFF & bytes[4];
		bits <<= 8;
		bits += 0xFF & bytes[3];
		bits <<= 8;
		bits += 0xFF & bytes[2];
		bits <<= 8;
		bits += 0xFF & bytes[1];
		bits <<= 8;
		bits += 0xFF & bytes[0];

		// Get double value
		double doubleValue = Double.longBitsToDouble(bits);

		return doubleValue;

	}

	@Override
	public int readInt16() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(2);

		if ((bytes[1] & 0x80) != 0) {

			// Get int value value
			int intValue = -(((~(bytes[1]) << 8) & 0x0000FF00) | (~(bytes[0]) & 0x000000FF)) - 1;

			return intValue;

		} else {

			// Get int value value
			int intValue = ((bytes[1] << 8) & 0x0000FF00)
					| (bytes[0] & 0x000000FF);

			return intValue;

		}

	}

	@Override
	public int readInt32() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(4);

		// Get int value value
		int intValue = 0xFF & bytes[3];
		intValue <<= 8;
		intValue += 0xFF & bytes[2];
		intValue <<= 8;
		intValue += 0xFF & bytes[1];
		intValue <<= 8;
		intValue += 0xFF & bytes[0];

		return intValue;

	}

	@Override
	public long readInt64() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(8);

		// Get long value
		long longValue = 0xFF & bytes[7];
		longValue <<= 8;
		longValue += 0xFF & bytes[6];
		longValue <<= 8;
		longValue += 0xFF & bytes[5];
		longValue <<= 8;
		longValue += 0xFF & bytes[4];
		longValue <<= 8;
		longValue += 0xFF & bytes[3];
		longValue <<= 8;
		longValue += 0xFF & bytes[2];
		longValue <<= 8;
		longValue += 0xFF & bytes[1];
		longValue <<= 8;
		longValue += 0xFF & bytes[0];

		return longValue;

	}

	@Override
	public int readInt8() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(1);

		// Get int value value
		int intValue = (int) bytes[0];

		return intValue;

	}

	@Override
	public String readString(int length) throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(length);

		// Get string value
		String stringValue = new String(bytes);

		return stringValue;

	}

	@Override
	public long readTime() throws IOException,
			RosTransportSerializationException {
		throw new NotYetImplementedException();
	}

	@Override
	public int readUInt16() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(2);

		// Get int value value
		int intValue = 0xFF & bytes[1];
		intValue <<= 8;
		intValue += 0xFF & bytes[0];

		return intValue;

	}

	@Override
	public long readUInt32() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(4);

		// Get long value
		long longValue = 0xFF & bytes[3];
		longValue <<= 8;
		longValue += 0xFF & bytes[2];
		longValue <<= 8;
		longValue += 0xFF & bytes[1];
		longValue <<= 8;
		longValue += 0xFF & bytes[0];

		return longValue;

	}

	@Override
	public BigInteger readUInt64() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(8);

		// Reverse bytes
		byte[] reversedBytes = BytesUtil.reverse(bytes);

		// Get big integer value
		BigInteger bigIntegerValue = new BigInteger(reversedBytes);

		return bigIntegerValue;

	}

	@Override
	public int readUInt8() throws IOException,
			RosTransportSerializationException {

		// Read bytes
		byte[] bytes = read(1);

		// Get int value value
		int intValue = bytes[0] & 0xFF;

		return intValue;

	}

	protected byte[] read(int length) throws IOException {

		byte[] bytes = new byte[length];

		for (int index = 0; index < length; index++) {

			// Read byte
			int result = inputStream.read();

			if (result != -1) {

				bytes[index] = (byte) result;

			} else {

				// Throw exception
				throw new IOException("unexpected end of file");

			}

		}

		return bytes;

	}

}
