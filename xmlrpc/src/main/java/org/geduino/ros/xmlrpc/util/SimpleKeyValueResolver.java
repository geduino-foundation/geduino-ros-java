package org.geduino.ros.xmlrpc.util;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class SimpleKeyValueResolver<K, V> implements KeyValueResolver<K, V> {

	@SuppressWarnings("unchecked")
	@Override
	public K getKey(Object keyObject) throws InvalidObjectTypeException {

		try {

			// Cast to K
			K key = (K) keyObject;

			return key;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public V getValue(Object valueObject) throws InvalidObjectTypeException {

		try {

			// Cast to V
			V value = (V) valueObject;

			return value;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

}
