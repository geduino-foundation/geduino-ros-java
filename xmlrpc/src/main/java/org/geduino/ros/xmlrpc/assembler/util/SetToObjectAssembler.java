package org.geduino.ros.xmlrpc.assembler.util;

import java.util.Iterator;
import java.util.Set;

import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.xmlrpc.assembler.util.impl.ProtocolTypeEntryToObjectAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class SetToObjectAssembler<E> {

	private final EntryToObjectAssembler<E> entryToObjectAssembler;

	public static final SetToObjectAssembler<ProtocolType> PROTOCOL_TYPE = new SetToObjectAssembler<ProtocolType>(
			new ProtocolTypeEntryToObjectAssembler());

	public SetToObjectAssembler(EntryToObjectAssembler<E> entryToObjectAssembler) {
		this.entryToObjectAssembler = entryToObjectAssembler;
	}

	public Object[] assemble(Set<E> set) throws InvalidObjectTypeException {

		// Get set size
		int size = set.size();

		// Create object array
		Object[] objects = new Object[size];

		// Init index
		int index = 0;

		for (Iterator<E> iterator = set.iterator(); iterator.hasNext();) {

			// Get next entry
			E entry = iterator.next();

			// Get object
			objects[index++] = entryToObjectAssembler.assemble(entry);

		}

		return objects;

	}

}
