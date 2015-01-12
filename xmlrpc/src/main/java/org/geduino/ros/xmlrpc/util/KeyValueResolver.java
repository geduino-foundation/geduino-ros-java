package org.geduino.ros.xmlrpc.util;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public interface KeyValueResolver<K, V> {

	K getKey(Object keyObject) throws InvalidObjectTypeException;

	V getValue(Object valueObject) throws InvalidObjectTypeException;

}
