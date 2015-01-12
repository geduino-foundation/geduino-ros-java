package org.geduino.ros.xmlrpc.util;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public interface EntryResolver<E> {

	E getEntry(Object entryObject) throws InvalidObjectTypeException;

}
