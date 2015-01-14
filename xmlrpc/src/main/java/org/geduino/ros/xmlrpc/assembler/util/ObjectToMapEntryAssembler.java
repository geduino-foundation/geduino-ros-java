package org.geduino.ros.xmlrpc.assembler.util;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public interface ObjectToMapEntryAssembler<K, V> {

	K assembleKey(Object object) throws InvalidObjectTypeException;
	
	V assembleValue(Object object) throws InvalidObjectTypeException;
	
}
