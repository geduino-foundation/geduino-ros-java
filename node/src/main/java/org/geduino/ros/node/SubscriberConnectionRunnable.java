package org.geduino.ros.node;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.transport.exception.RosTransportSerializationException;
import org.geduino.ros.core.transport.model.SubscriberConnection;

class SubscriberConnectionRunnable<T extends Message> implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(Subscriber.class);

	private final SubscriberConnection<T> subscriberConnection;
	private final SubscriberListener<T> subscriberListener;

	SubscriberConnectionRunnable(SubscriberConnection<T> subscriberConnection,
			SubscriberListener<T> subscriberListener) {
		this.subscriberConnection = subscriberConnection;
		this.subscriberListener = subscriberListener;
	}

	@Override
	public void run() {

		// Log
		LOGGER.trace("starting subscriber connection thread for: "
				+ subscriberConnection);

		while (subscriberConnection.getBusInfo().isConnected()) {

			try {

				// Read message
				T message = subscriberConnection.getMessageReader().read();

				// Fire message received listener
				subscriberListener.messageReceived(message);

			} catch (IOException ex) {

				// Log
				LOGGER.error("could not read message", ex);

			} catch (RosTransportSerializationException ex) {

				// Log
				LOGGER.error("could not read message", ex);

			}

		}

		// Log
		LOGGER.trace("subscriber connection thread ended for: "
				+ subscriberConnection);

	}

}
