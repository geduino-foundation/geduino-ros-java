package org.geduino.ros.core.transport.model;

import java.io.IOException;
import java.math.BigInteger;

import org.geduino.ros.core.transport.exception.RosTransportSerializationException;

public interface PrimitiveTypeReader {

	boolean readBool() throws IOException, RosTransportSerializationException;

	float readFloat32() throws IOException, RosTransportSerializationException;

	double readFloat64() throws IOException, RosTransportSerializationException;

	int readInt8() throws IOException, RosTransportSerializationException;

	int readInt16() throws IOException, RosTransportSerializationException;

	int readInt32() throws IOException, RosTransportSerializationException;

	long readInt64() throws IOException, RosTransportSerializationException;

	int readUInt8() throws IOException, RosTransportSerializationException;

	int readUInt16() throws IOException, RosTransportSerializationException;

	long readUInt32() throws IOException, RosTransportSerializationException;

	BigInteger readUInt64() throws IOException,
			RosTransportSerializationException;

	String readString(int length) throws IOException, RosTransportSerializationException;

	long readTime() throws IOException, RosTransportSerializationException;

	long readDuration() throws IOException, RosTransportSerializationException;

}
