package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToMapEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class GlobalNameMessageNameToMapEntryAssembler implements
		ObjectToMapEntryAssembler<GlobalName, MessageName> {

	@Override
	public GlobalName assembleKey(Object object)
			throws InvalidObjectTypeException {

		try {

			// Cast to String
			String string = (String) object;

			// Parse global name
			GlobalName globalName = (GlobalName) Name.parseName(string);

			return globalName;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

	@Override
	public MessageName assembleValue(Object object)
			throws InvalidObjectTypeException {

		// Cast to String
		String string = (String) object;

		// Parse message name
		MessageName messageName = Name.parseMessageName(string);

		return messageName;

	}

}
