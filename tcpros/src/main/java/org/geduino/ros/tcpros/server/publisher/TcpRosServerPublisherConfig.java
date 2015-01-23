package org.geduino.ros.tcpros.server.publisher;

import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.tcpros.server.TcpRosServerConfig;

public class TcpRosServerPublisherConfig<M extends Message> extends
		TcpRosServerConfig<TcpRosServerPublisherConnection<M>> {

	public TcpRosServerPublisherConfig(
			GlobalName callerId,
			int port,
			int backLog,
			TcpRosServerPublisherConnectionFactory<M> tcpRosServerPublisherConnectionFactory) {

		super(callerId, port, backLog, tcpRosServerPublisherConnectionFactory);

	}

}
