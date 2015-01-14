package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.xmlrpc.assembler.util.MapEntryToObjectAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ToStringMapEntryToObjectAssembler<K, V> implements
		MapEntryToObjectAssembler<K, V> {

	@Override
	public Object assembleKey(K key) throws InvalidObjectTypeException {
		return key.toString();
	}

	@Override
	public Object assembleValue(V value) throws InvalidObjectTypeException {
		return value.toString();
	}

}
