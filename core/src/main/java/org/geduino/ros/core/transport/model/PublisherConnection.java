package org.geduino.ros.core.transport.model;

import java.io.IOException;

import org.geduino.ros.core.api.model.PublisherConnectionData;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageWriter;

public interface PublisherConnection<T extends Message> extends TopicConnection {

	PublisherConnectionData getPublisherConnectionData();

	MessageWriter<T> getMessageWriter() throws IOException;

}
