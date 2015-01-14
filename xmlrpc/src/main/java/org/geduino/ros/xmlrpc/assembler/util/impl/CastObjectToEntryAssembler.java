package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.xmlrpc.assembler.util.ObjectToEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class CastObjectToEntryAssembler<E> implements ObjectToEntryAssembler<E> {
	
	@Override
	public E assemble(Object object) throws InvalidObjectTypeException {
		
		try {

			// Cast to E
			@SuppressWarnings("unchecked")
			E entry = (E) object;

			return entry;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}
		
	}

}
