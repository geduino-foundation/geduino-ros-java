package org.geduino.ros.tcpros;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Arrays;

import org.geduino.ros.core.exception.NotYetImplementedException;
import org.geduino.ros.core.transport.exception.RosTransportSerializationException;
import org.geduino.ros.core.transport.model.PrimitiveTypeWriter;
import org.geduino.ros.core.util.BytesUtil;

public class OutputStreamPrimitiveTypeWriter implements PrimitiveTypeWriter {

	private final OutputStream outputStream;

	public OutputStreamPrimitiveTypeWriter(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void writeBool(boolean bool) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = new byte[1];

		if (bool) {
			bytes[0] = 0x01;
		} else {
			bytes[0] = 0x00;
		}

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeDuration(long duration) throws IOException,
			RosTransportSerializationException {
		throw new NotYetImplementedException();
	}

	@Override
	public void writeFloat32(float float32) throws IOException,
			RosTransportSerializationException {

		// Get bit
		int bits = Float.floatToIntBits(float32);

		// Get bytes
		byte[] bytes = new byte[4];
		bytes[0] = (byte) bits;
		bytes[1] = (byte) (bits >> 8);
		bytes[2] = (byte) (bits >> 16);
		bytes[3] = (byte) (bits >> 24);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeFloat64(double float64) throws IOException,
			RosTransportSerializationException {

		// Get bit
		long bits = Double.doubleToLongBits(float64);

		// Get bytes
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (bits);
		bytes[1] = (byte) (bits >> 8);
		bytes[2] = (byte) (bits >> 16);
		bytes[3] = (byte) (bits >> 24);
		bytes[4] = (byte) (bits >> 32);
		bytes[5] = (byte) (bits >> 40);
		bytes[6] = (byte) (bits >> 48);
		bytes[7] = (byte) (bits >> 56);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeInt16(int int16) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = new byte[2];
		bytes[0] = (byte) int16;
		bytes[1] = (byte) (int16 >> 8);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeInt32(int int32) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = new byte[4];
		bytes[0] = (byte) int32;
		bytes[1] = (byte) (int32 >> 8);
		bytes[2] = (byte) (int32 >> 16);
		bytes[3] = (byte) (int32 >> 24);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeInt64(long int64) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (int64);
		bytes[1] = (byte) (int64 >> 8);
		bytes[2] = (byte) (int64 >> 16);
		bytes[3] = (byte) (int64 >> 24);
		bytes[4] = (byte) (int64 >> 32);
		bytes[5] = (byte) (int64 >> 40);
		bytes[6] = (byte) (int64 >> 48);
		bytes[7] = (byte) (int64 >> 56);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeInt8(int int8) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = new byte[1];
		bytes[0] = (byte) int8;

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeString(String string) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = string.getBytes();

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeTime(long time) throws IOException,
			RosTransportSerializationException {
		throw new NotYetImplementedException();
	}

	@Override
	public void writeUInt16(int uInt16) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = new byte[2];
		bytes[0] = (byte) uInt16;
		bytes[1] = (byte) (uInt16 >> 8);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeUInt32(long uInt32) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = new byte[4];
		bytes[0] = (byte) uInt32;
		bytes[1] = (byte) (uInt32 >> 8);
		bytes[2] = (byte) (uInt32 >> 16);
		bytes[3] = (byte) (uInt32 >> 24);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeUInt64(BigInteger uInt64) throws IOException,
			RosTransportSerializationException {

		// Get big integer bytes
		byte[] bigIntegerBytes = uInt64.toByteArray();

		// Fill a 8 byte array
		byte[] bytes8 = new byte[8];
		Arrays.fill(bytes8, (byte) 0);
		int offset = 8 - bigIntegerBytes.length;
		for (int index = 0; index < bigIntegerBytes.length; index++) {
			bytes8[offset + index] = bigIntegerBytes[index];
		}

		// Reverse bytes
		byte[] reversedBytes = BytesUtil.reverse(bytes8);

		// Write bytes
		outputStream.write(reversedBytes);

	}

	@Override
	public void writeUInt8(int uInt8) throws IOException,
			RosTransportSerializationException {

		// Get bytes
		byte[] bytes = new byte[1];
		bytes[0] = (byte) uInt8;

		// Write bytes
		outputStream.write(bytes);

	}

}
