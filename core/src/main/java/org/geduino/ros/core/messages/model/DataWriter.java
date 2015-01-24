package org.geduino.ros.core.messages.model;

import java.io.IOException;
import java.math.BigInteger;

import org.geduino.ros.core.messages.exception.RosMessageSerializationException;

public interface DataWriter {

	void writeBool(boolean bool) throws IOException,
			RosMessageSerializationException;

	void writeFloat32(float float32) throws IOException,
			RosMessageSerializationException;

	void writeFloat64(double float64) throws IOException,
			RosMessageSerializationException;

	void writeInt8(int int8) throws IOException,
			RosMessageSerializationException;

	void writeInt16(int int16) throws IOException,
			RosMessageSerializationException;

	void writeInt32(int int32) throws IOException,
			RosMessageSerializationException;

	void writeInt64(long int64) throws IOException,
			RosMessageSerializationException;

	void writeUInt8(int uInt8) throws IOException,
			RosMessageSerializationException;

	void writeUInt16(int uInt16) throws IOException,
			RosMessageSerializationException;

	void writeUInt32(long uInt32) throws IOException,
			RosMessageSerializationException;

	void writeUInt64(BigInteger uInt64) throws IOException,
			RosMessageSerializationException;

	void writeString(String string) throws IOException,
			RosMessageSerializationException;

	void writeTime(long time) throws IOException,
			RosMessageSerializationException;

	void writeDuration(long duration) throws IOException,
			RosMessageSerializationException;

}
