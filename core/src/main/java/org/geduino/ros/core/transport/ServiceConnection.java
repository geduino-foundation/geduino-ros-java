package org.geduino.ros.core.transport;


import java.io.IOException;

import org.geduino.ros.core.api.model.Message;

public interface ServiceConnection extends Connection {

	String getServiceName();
	
	Message readMessage() throws IOException;
	
	void writeMessage(Message message) throws IOException;
	
}
