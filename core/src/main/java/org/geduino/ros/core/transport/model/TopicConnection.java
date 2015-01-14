package org.geduino.ros.core.transport.model;

import org.geduino.ros.core.api.model.BusInfo;


public interface TopicConnection extends Connection {

	BusInfo getBusInfo();
	
}
