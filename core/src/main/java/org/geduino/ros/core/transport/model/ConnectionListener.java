package org.geduino.ros.core.transport.model;

import org.geduino.ros.core.messages.model.Message;


public interface ConnectionListener<T extends Message, K extends Message> {

	void incomingPublisherConnection(PublisherConnection<K> publisherConnection);

	void incomingServiceConnection(ServiceConnection<T, K> serviceConnection);

}
