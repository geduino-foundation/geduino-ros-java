package org.geduino.ros.core.transport.model;

import org.geduino.ros.core.api.model.PublisherConnectionData;
import org.geduino.ros.core.messages.model.Message;

public interface PublisherConnection<T extends Message> extends TopicConnection {

	PublisherConnectionData getPublisherConnectionData();

	MessageWriter<T> getMessageWriter();

}