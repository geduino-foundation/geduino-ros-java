package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.xmlrpc.assembler.util.EntryToObjectAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ProtocolTypeEntryToObjectAssembler implements
		EntryToObjectAssembler<ProtocolType> {

	@Override
	public Object assemble(ProtocolType entry)
			throws InvalidObjectTypeException {

		// Create objects
		Object[] objects = new Object[] { entry.name() };

		return objects;
		
	}

}
