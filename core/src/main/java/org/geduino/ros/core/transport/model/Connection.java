package org.geduino.ros.core.transport.model;

import java.io.IOException;

public interface Connection {
	
	String getConnectionId();
	
	void close() throws IOException;

}
