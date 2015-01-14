package org.geduino.ros.tcpros.client;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.geduino.ros.core.api.model.TcpRosProtocol;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.transport.model.SubscriberConnection;
import org.geduino.ros.tcpros.TcpRosConnection;
import org.geduino.ros.tcpros.exception.TcpRosException;

public class TcpRosClient<T extends Message> {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosConnection.class);

	public SubscriberConnection<T> open(GlobalName callerId,
			TcpRosProtocol tcpRosProtocol, GlobalName topic,
			MessageDetails<T> messageDetails) throws TcpRosException,
			IOException {

		// Get host and port
		String host = tcpRosProtocol.getHost();
		int port = tcpRosProtocol.getPort();

		// Log
		LOGGER.trace("open tcpros connection to host: " + host + ", on port: "
				+ port + " ...");

		// Create new socket
		Socket socket = new Socket(host, port);

		// Create tcp ros client subscriber connection
		TcpRosClientSubscriberConnection<T> tcpRosClientSubscriberConnection = new TcpRosClientSubscriberConnection<T>(
				callerId, socket, topic, messageDetails);

		return tcpRosClientSubscriberConnection;

	}

}
