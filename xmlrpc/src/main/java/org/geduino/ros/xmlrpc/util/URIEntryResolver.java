package org.geduino.ros.xmlrpc.util;


import java.net.URI;
import java.net.URISyntaxException;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class URIEntryResolver implements EntryResolver<URI> {

	@Override
	public URI getEntry(Object entryObject) throws InvalidObjectTypeException {

		try {

			// Get entry as string
			String entry = (String) entryObject;

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
