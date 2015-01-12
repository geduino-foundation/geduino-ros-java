package org.geduino.ros.xmlrpc.util;


import java.util.Set;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class StringSetKeyValueResolver<E> implements
		KeyValueResolver<String, Set<E>> {

	private final SetUtil<E> setUtil;

	public StringSetKeyValueResolver(EntryResolver<E> entryResolver) {
		this.setUtil = new SetUtil<E>(entryResolver);
	}

	@Override
	public String getKey(Object keyObject) throws InvalidObjectTypeException {

		try {
			
			// Cast to String
			String key = (String) keyObject;

			return key;

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

	@Override
	public Set<E> getValue(Object valueObject) throws InvalidObjectTypeException {
		return setUtil.toSet((Object[]) valueObject);
	}

}
