package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.xmlrpc.assembler.util.EntryToObjectAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ToStringEntryToObjectAssembler<E> implements
		EntryToObjectAssembler<E> {

	@Override
	public Object assemble(E entry) throws InvalidObjectTypeException {
		return entry.toString();
	}

}
