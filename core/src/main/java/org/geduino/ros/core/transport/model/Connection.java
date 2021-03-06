package org.geduino.ros.core.transport.model;

import java.io.IOException;

import org.geduino.ros.core.api.model.Protocol;

public interface Connection {

	String getConnectionId();

	Protocol getConnectionProtocol();

	boolean isClosed();
	
	void close() throws IOException;

}
