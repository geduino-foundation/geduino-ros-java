package org.geduino.ros.core.transport.model;

import org.geduino.ros.core.api.model.SubscriberConnectionData;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageReader;

public interface SubscriberConnection<T extends Message> extends
		TopicConnection {

	SubscriberConnectionData getSubscriberConnectionData();

	MessageReader<T> getMessageReader();

}
