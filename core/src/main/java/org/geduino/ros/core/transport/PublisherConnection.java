package org.geduino.ros.core.transport;


import java.io.IOException;

import org.geduino.ros.core.api.model.Message;
import org.geduino.ros.core.api.model.PublisherConnectionData;


public interface PublisherConnection extends TopicConnection {

	PublisherConnectionData getPublisherConnectionData();
	
	void writeMessage(Message message) throws IOException;
	
}
