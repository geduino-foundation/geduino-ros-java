package org.geduino.ros.core.messages.model;

import java.io.IOException;

import org.geduino.ros.core.messages.exception.RosMessageSerializationException;

public interface MessageWriter<T extends Message> {

	void write(T message) throws IOException, RosMessageSerializationException;

}
