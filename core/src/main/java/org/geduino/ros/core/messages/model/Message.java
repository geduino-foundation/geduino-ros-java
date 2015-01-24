package org.geduino.ros.core.messages.model;

import java.io.IOException;

import org.geduino.ros.core.messages.exception.RosMessageSerializationException;

public abstract class Message {

	public abstract void serialize(DataWriter dataWriter) throws IOException,
			RosMessageSerializationException;

	public abstract void deserialize(DataReader dataReader) throws IOException,
			RosMessageSerializationException;

}
