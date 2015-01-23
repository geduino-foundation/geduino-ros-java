package org.geduino.ros.core.transport.model;

public interface ConnectionListener<C extends Connection> {

	void incomingConnection(C connection);

}
