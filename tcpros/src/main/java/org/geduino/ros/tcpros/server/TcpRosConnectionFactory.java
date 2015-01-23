package org.geduino.ros.tcpros.server;

import java.io.IOException;
import java.net.Socket;

import org.geduino.ros.tcpros.TcpRosConnection;
import org.geduino.ros.tcpros.exception.TcpRosException;

public interface TcpRosConnectionFactory<C extends TcpRosConnection> {

	C newConnection(Socket socket, TcpRosServerConfig<C> tcpRosServerConfig)
			throws IOException, TcpRosException;

}
