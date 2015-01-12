package org.geduino.ros.xmlrpc.util;


import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class SetUtil<E> {

	private final EntryResolver<E> entryResolver;

	public static final SetUtil<URI> E_URI = new SetUtil<URI>(
			new URIEntryResolver());
	
	public static final SetUtil<Protocol> E_PROTOCOL = new SetUtil<Protocol>(
			new ProtocolEntryResolver());

	public SetUtil(EntryResolver<E> entryResolver) {
		this.entryResolver = entryResolver;
	}

	public Set<E> toSet(Object[] array) throws InvalidObjectTypeException {

		Set<E> set = new HashSet<E>();

		for (int index = 0; index < array.length; index++) {

			// Get entry
			E entry = entryResolver.getEntry(array[index]);

			set.add(entry);

		}

		return set;

	}

}
