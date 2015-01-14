package org.geduino.ros.core.transport.model;

import org.geduino.ros.core.messages.model.Message;

public interface ServiceConnection<T extends Message, K extends Message>
		extends Connection {

	String getServiceName();

	MessageReader<T> getMessageReader();

	MessageWriter<K> getMessageWriter();

}
