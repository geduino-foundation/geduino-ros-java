package org.geduino.ros.tcpros.server.publisher;

import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.tcpros.server.TcpRosServer;

public class TcpRosServerPublisher<M extends Message> extends
		TcpRosServer<TcpRosServerPublisherConnection<M>> {

	public TcpRosServerPublisher(
			TcpRosServerPublisherConfig<M> tcpRosServerPublisherConfig) {
		super(tcpRosServerPublisherConfig);
	}

}
