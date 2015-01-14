package org.geduino.ros.xmlrpc.assembler.util.impl;

import org.geduino.ros.xmlrpc.assembler.util.ObjectToMapEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class CastObjectToMapEntryAssembler<K, V> implements
		ObjectToMapEntryAssembler<K, V> {

	@Override
	public K assembleKey(Object object) throws InvalidObjectTypeException {

		try {

			// Cast to K
			@SuppressWarnings("unchecked")
			K key = (K) object;

			return key;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

	@Override
	public V assembleValue(Object object) throws InvalidObjectTypeException {

		try {

			// Cast to V
			@SuppressWarnings("unchecked")
			V value = (V) object;

			return value;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

}
