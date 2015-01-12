package org.geduino.ros.xmlrpc.util;


import java.util.Arrays;

import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ProtocolEntryResolver implements EntryResolver<Protocol> {

	@Override
	public Protocol getEntry(Object entryObject)
			throws InvalidObjectTypeException {

		try {

			// Cast to object array
			Object[] objects = (Object[]) entryObject;

			// Get protocol name
			String name = (String) objects[0];

			// Get protocol arguments
			Object[] arguments = Arrays.copyOfRange(objects, 1, objects.length);

			// Create protocol
			Protocol protocol = new Protocol(name, arguments);

			return protocol;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

}
