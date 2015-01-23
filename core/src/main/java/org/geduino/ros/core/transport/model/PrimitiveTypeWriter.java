package org.geduino.ros.core.transport.model;

import java.io.IOException;
import java.math.BigInteger;

import org.geduino.ros.core.transport.exception.RosTransportSerializationException;

public interface PrimitiveTypeWriter {

	void writeBool(boolean bool) throws IOException,
			RosTransportSerializationException;

	void writeFloat32(float float32) throws IOException,
			RosTransportSerializationException;

	void writeFloat64(double float64) throws IOException,
			RosTransportSerializationException;

	void writeInt8(int int8) throws IOException,
			RosTransportSerializationException;

	void writeInt16(int int16) throws IOException,
			RosTransportSerializationException;

	void writeInt32(int int32) throws IOException,
			RosTransportSerializationException;

	void writeInt64(long int64) throws IOException,
			RosTransportSerializationException;

	void writeUInt8(int uInt8) throws IOException,
			RosTransportSerializationException;

	void writeUInt16(int uInt16) throws IOException,
			RosTransportSerializationException;

	void writeUInt32(long uInt32) throws IOException,
			RosTransportSerializationException;

	void writeUInt64(BigInteger uInt64) throws IOException,
			RosTransportSerializationException;

	void writeString(String string) throws IOException,
			RosTransportSerializationException;

	void writeTime(long time) throws IOException,
			RosTransportSerializationException;

	void writeDuration(long duration) throws IOException,
			RosTransportSerializationException;

}
