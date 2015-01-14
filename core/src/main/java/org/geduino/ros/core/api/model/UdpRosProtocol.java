package org.geduino.ros.core.api.model;

public class UdpRosProtocol extends Protocol {

	private static final long serialVersionUID = 1L;

	public UdpRosProtocol() {
		super(ProtocolType.UDPROS);
	}

	@Override
	public String toString() {
		return "UdpRosProtocol []";
	}

}
