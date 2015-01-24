package org.geduino.ros.core.messages.model;

import java.io.IOException;
import java.math.BigInteger;

import org.geduino.ros.core.messages.exception.RosMessageSerializationException;

public interface DataReader {

	boolean readBool() throws IOException, RosMessageSerializationException;

	float readFloat32() throws IOException, RosMessageSerializationException;

	double readFloat64() throws IOException, RosMessageSerializationException;

	int readInt8() throws IOException, RosMessageSerializationException;

	int readInt16() throws IOException, RosMessageSerializationException;

	int readInt32() throws IOException, RosMessageSerializationException;

	long readInt64() throws IOException, RosMessageSerializationException;

	int readUInt8() throws IOException, RosMessageSerializationException;

	int readUInt16() throws IOException, RosMessageSerializationException;

	long readUInt32() throws IOException, RosMessageSerializationException;

	BigInteger readUInt64() throws IOException,
			RosMessageSerializationException;

	String readString(int length) throws IOException,
			RosMessageSerializationException;

	long readTime() throws IOException, RosMessageSerializationException;

	long readDuration() throws IOException, RosMessageSerializationException;

}
