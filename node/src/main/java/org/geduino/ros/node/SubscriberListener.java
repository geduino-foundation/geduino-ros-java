package org.geduino.ros.node;

import java.net.URI;

import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.naming.model.GlobalName;

public interface SubscriberListener<T extends Message> {

	Class<T> getMessageClass();
	
	void onMessage(URI callerApi, GlobalName topic, T message);

}
