package org.geduino.ros.tcpros;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Duration;
import org.geduino.ros.core.messages.model.Time;
import org.geduino.ros.core.util.BytesUtil;

public class OutputStreamDataWriter implements DataWriter {

	private static final Logger LOGGER = Logger
			.getLogger(OutputStreamDataWriter.class);

	private final OutputStream outputStream;

	private int byteSent;

	public OutputStreamDataWriter(OutputStream outputStream) {

		this.outputStream = outputStream;

		// Set byte send to zero
		byteSent = 0;

	}

	@Override
	public void writeBool(boolean bool) throws IOException,
			RosMessageSerializationException {

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
	public void writeDuration(Duration duration) throws IOException,
			RosMessageSerializationException {

		// Get seconds and nanos
		int seconds = (int) duration.getSeconds();
		int nanos = (int) duration.getNanos();

		// Write seconds and nanos as int32
		writeInt32(seconds);
		writeInt32(nanos);

	}

	@Override
	public void writeFloat32(float float32) throws IOException,
			RosMessageSerializationException {

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
			RosMessageSerializationException {

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
			RosMessageSerializationException {

		// Get bytes
		byte[] bytes = new byte[2];
		bytes[0] = (byte) int16;
		bytes[1] = (byte) (int16 >> 8);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeInt32(int int32) throws IOException,
			RosMessageSerializationException {

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
			RosMessageSerializationException {

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
			RosMessageSerializationException {

		// Get bytes
		byte[] bytes = new byte[1];
		bytes[0] = (byte) int8;

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeString(String string) throws IOException,
			RosMessageSerializationException {

		// Get bytes
		byte[] bytes = string.getBytes();

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeTime(Time time) throws IOException,
			RosMessageSerializationException {

		// Get seconds and nanos
		long seconds = time.getSeconds();
		long nanos = time.getNanos();

		// Write seconds and nanos as int32
		writeUInt32(seconds);
		writeUInt32(nanos);

	}

	@Override
	public void writeUInt16(int uInt16) throws IOException,
			RosMessageSerializationException {

		// Get bytes
		byte[] bytes = new byte[2];
		bytes[0] = (byte) uInt16;
		bytes[1] = (byte) (uInt16 >> 8);

		// Write bytes
		outputStream.write(bytes);

	}

	@Override
	public void writeUInt32(long uInt32) throws IOException,
			RosMessageSerializationException {

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
			RosMessageSerializationException {

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
			RosMessageSerializationException {

		// Get bytes
		byte[] bytes = new byte[1];
		bytes[0] = (byte) uInt8;

		// Write bytes
		outputStream.write(bytes);

	}

	public int getByteSent() {
		return byteSent;
	}

	protected void write(byte[] bytes) throws IOException {

		// Log
		LOGGER.trace("writing " + bytes.length + " bytes...");

		// Write bytes
		outputStream.write(bytes);

		// Increase byte sent
		byteSent += bytes.length;

	}

}
