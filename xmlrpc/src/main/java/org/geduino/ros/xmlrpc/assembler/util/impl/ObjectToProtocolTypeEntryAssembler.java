package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ObjectToProtocolTypeEntryAssembler implements
		ObjectToEntryAssembler<ProtocolType> {

	@Override
	public ProtocolType assemble(Object object)
			throws InvalidObjectTypeException {

		try {
			
			// Cast to object array
			Object[] objects = (Object[]) object;
			
			// Cast to string
			String string = (String) objects[0];

			// Get protocol type
			ProtocolType protocolType = ProtocolType.valueOf(string
					.toUpperCase());

			return protocolType;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		} catch (IllegalArgumentException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unknow protocol type", ex);

		}

	}

}
