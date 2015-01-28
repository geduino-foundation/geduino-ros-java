package org.geduino.ros.core.transport.model;

import java.io.IOException;

import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageReader;
import org.geduino.ros.core.messages.model.MessageWriter;

public interface ServiceConnection<T extends Message, K extends Message>
		extends Connection {

	String getServiceName();

	MessageReader<T> getMessageReader() throws IOException;

	MessageWriter<K> getMessageWriter() throws IOException;

}
