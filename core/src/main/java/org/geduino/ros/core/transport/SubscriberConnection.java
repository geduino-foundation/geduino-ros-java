package org.geduino.ros.core.transport;


import java.io.IOException;

import org.geduino.ros.core.api.model.Message;
import org.geduino.ros.core.api.model.SubscriberConnectionData;

public interface SubscriberConnection extends TopicConnection {

	SubscriberConnectionData getSubscriberConnectionData();

	Message readMessage() throws IOException;

}
