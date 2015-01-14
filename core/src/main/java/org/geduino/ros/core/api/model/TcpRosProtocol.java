package org.geduino.ros.core.api.model;

public class TcpRosProtocol extends Protocol {

	private static final long serialVersionUID = 1L;

	private final String host;
	private final int port;

	public TcpRosProtocol(String host, int port) {

		super(ProtocolType.TCPROS);

		this.host = host;
		this.port = port;

	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		return "TcpRosProtocol [host=" + host + ", port=" + port + "]";
	}

}
