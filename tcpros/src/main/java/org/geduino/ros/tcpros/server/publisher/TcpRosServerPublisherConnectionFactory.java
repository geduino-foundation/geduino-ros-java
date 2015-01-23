package org.geduino.ros.tcpros.server.publisher;

import java.io.IOException;
import java.net.Socket;

import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.tcpros.exception.TcpRosException;
import org.geduino.ros.tcpros.server.TcpRosConnectionFactory;
import org.geduino.ros.tcpros.server.TcpRosServerConfig;

public class TcpRosServerPublisherConnectionFactory<M extends Message>
		implements TcpRosConnectionFactory<TcpRosServerPublisherConnection<M>> {

	private final GlobalName topic;
	private final MessageDetails<M> messageDetails;

	public TcpRosServerPublisherConnectionFactory(GlobalName topic,
			MessageDetails<M> messageDetails) {
		this.topic = topic;
		this.messageDetails = messageDetails;
	}

	@Override
	public TcpRosServerPublisherConnection<M> newConnection(
			Socket socket,
			TcpRosServerConfig<TcpRosServerPublisherConnection<M>> tcpRosServerConfig)
			throws IOException, TcpRosException {

		// Create tcp ros server publisher connection
		TcpRosServerPublisherConnection<M> tcpRosServerPublisherConnection = new TcpRosServerPublisherConnection<M>(
				tcpRosServerConfig.getCallerId(), topic, messageDetails, socket);

		return tcpRosServerPublisherConnection;

	}

}
