package org.geduino.ros.core.transport.model;

import java.io.IOException;

import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.transport.exception.RosTransportSerializationException;

public interface MessageReader<T extends Message> {

	T read() throws IOException, RosTransportSerializationException;

}
