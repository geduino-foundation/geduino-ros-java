package org.geduino.ros.xmlrpc.assembler.util;

import java.util.Iterator;
import java.util.Map;

import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.xmlrpc.assembler.util.impl.ToStringMapEntryToObjectAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class MapToObjectAssembler<K, V> {

	private final MapEntryToObjectAssembler<K, V> mapEntryToObjectAssembler;

	public static final MapToObjectAssembler<GlobalName, MessageName> K_GLOBAL_NAME_V_MESSAGE_NAME = new MapToObjectAssembler<GlobalName, MessageName>(
			new ToStringMapEntryToObjectAssembler<GlobalName, MessageName>());

	public MapToObjectAssembler(
			MapEntryToObjectAssembler<K, V> mapEntryToObjectAssembler) {
		this.mapEntryToObjectAssembler = mapEntryToObjectAssembler;
	}

	public Object[] assemble(Map<K, V> map) throws InvalidObjectTypeException {

		// Get map size
		int size = map.size();

		// Create object array
		Object[] objects = new Object[size];

		// Init index
		int index = 0;

		for (Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator(); iterator
				.hasNext();) {

			// Get next entry
			Map.Entry<K, V> entry = iterator.next();

			// Get key and value
			Object key = mapEntryToObjectAssembler.assembleKey(entry.getKey());
			Object value = mapEntryToObjectAssembler.assembleValue(entry
					.getValue());

			// Add to objects
			objects[index++] = new Object[] { key, value };

		}

		return objects;

	}

}
