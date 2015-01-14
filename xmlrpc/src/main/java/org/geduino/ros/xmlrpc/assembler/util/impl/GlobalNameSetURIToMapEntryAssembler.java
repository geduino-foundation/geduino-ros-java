package org.geduino.ros.xmlrpc.assembler.util.impl;

import java.net.URI;
import java.util.Set;

import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToMapEntryAssembler;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToSetAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class GlobalNameSetURIToMapEntryAssembler implements
		ObjectToMapEntryAssembler<GlobalName, Set<URI>> {

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
	public Set<URI> assembleValue(Object object)
			throws InvalidObjectTypeException {

		try {

			// Cast to object array
			Object[] objects = (Object[]) object;

			// Assemble uris
			Set<URI> uris = ObjectToSetAssembler.URI.assemble(objects);

			return uris;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

}
