package org.geduino.ros.xmlrpc.assembler.util;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public interface ObjectToEntryAssembler<E> {

	E assemble(Object object) throws InvalidObjectTypeException;
	
}
