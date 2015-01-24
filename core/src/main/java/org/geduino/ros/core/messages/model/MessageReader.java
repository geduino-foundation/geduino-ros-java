package org.geduino.ros.core.messages.model;

import java.io.IOException;

import org.geduino.ros.core.messages.exception.RosMessageSerializationException;

public interface MessageReader<T extends Message> {

	T read() throws IOException, RosMessageSerializationException;

}
