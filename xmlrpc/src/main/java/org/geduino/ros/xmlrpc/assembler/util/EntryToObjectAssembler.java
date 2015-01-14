package org.geduino.ros.xmlrpc.assembler.util;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public interface EntryToObjectAssembler<E> {

	Object assemble(E entry) throws InvalidObjectTypeException;

}
