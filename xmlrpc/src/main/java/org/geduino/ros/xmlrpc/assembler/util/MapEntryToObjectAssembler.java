package org.geduino.ros.xmlrpc.assembler.util;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public interface MapEntryToObjectAssembler<K, V> {

	Object assembleKey(K key) throws InvalidObjectTypeException;

	Object assembleValue(V value) throws InvalidObjectTypeException;

}
