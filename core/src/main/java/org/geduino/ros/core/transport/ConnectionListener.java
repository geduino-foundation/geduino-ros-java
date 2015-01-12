package org.geduino.ros.core.transport;


public interface ConnectionListener {

	void incomingPublisherConnection(PublisherConnection publisherConnection);

	void incomingServiceConnection(ServiceConnection serviceConnection);

}
