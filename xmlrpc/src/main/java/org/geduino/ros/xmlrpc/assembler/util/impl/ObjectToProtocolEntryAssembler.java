package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.core.api.model.TcpRosProtocol;
import org.geduino.ros.core.api.model.UdpRosProtocol;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ObjectToProtocolEntryAssembler implements
		ObjectToEntryAssembler<Protocol> {

	public static final ObjectToProtocolEntryAssembler INSTANCE = new ObjectToProtocolEntryAssembler();

	private ObjectToProtocolEntryAssembler() {
	}

	@Override
	public Protocol assemble(Object object) throws InvalidObjectTypeException {

		try {

			// Cast to object array
			Object[] objects = (Object[]) object;

			// Get protocol name
			String protocolName = (String) objects[0];

			// Get protocol type
			ProtocolType protocolType = ProtocolType.valueOf(protocolName
					.toUpperCase());

			if (protocolType == ProtocolType.TCPROS) {

				// Get host and port
				String host = (String) objects[1];
				int port = ((Integer) objects[2]).intValue();

				// Create tcp ros protocol
				TcpRosProtocol tcpRosProtocol = new TcpRosProtocol(host, port);

				return tcpRosProtocol;

			} else if (protocolType == ProtocolType.UDPROS) {

				// Create udp ros protocol
				UdpRosProtocol udpRosProtocol = new UdpRosProtocol();

				return udpRosProtocol;

			} else {
				throw new InvalidObjectTypeException("unknow protocol name: "
						+ protocolName);
			}

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

}
