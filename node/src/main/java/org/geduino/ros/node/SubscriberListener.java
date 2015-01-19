package org.geduino.ros.node;

import org.geduino.ros.core.messages.model.Message;

public interface SubscriberListener<T extends Message> {

	void messageReceived(T message);

}
