package org.geduino.ros.xmlrpc.assembler.util.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.geduino.ros.xmlrpc.assembler.util.ObjectToEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class URIObjectToEntryAssembler implements ObjectToEntryAssembler<URI> {

	@Override
	public URI assemble(Object object) throws InvalidObjectTypeException {

		try {

			// Get entry as string
			String entry = (String) object;

			// Create URI
			URI uri = new URI(entry);

			return uri;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		} catch (URISyntaxException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("malformed uri", ex);

		}

	}

}
