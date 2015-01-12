package org.geduino.ros.xmlrpc.util;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class SimpleEntryResolver<E> implements EntryResolver<E> {

	@SuppressWarnings("unchecked")
	@Override
	public E getEntry(Object entryObject) throws InvalidObjectTypeException {

		try {

			// Cast to E
			E entry = (E) entryObject;

			return entry;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

}
