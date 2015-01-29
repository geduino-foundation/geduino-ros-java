package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ObjectToGlobalNameEntryAssembler implements
		ObjectToEntryAssembler<GlobalName> {

	@Override
	public GlobalName assemble(Object object) throws InvalidObjectTypeException {

		try {

			// Cast to string
			String string = (String) object;

			// Get global name
			GlobalName globalName = (GlobalName) Name
					.parseName(string);

			return globalName;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

}
