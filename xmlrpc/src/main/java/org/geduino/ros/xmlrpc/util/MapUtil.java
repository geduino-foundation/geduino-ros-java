package org.geduino.ros.xmlrpc.util;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class MapUtil<K, V> {

	private final KeyValueResolver<K, V> keyValueResolver;

	public static final MapUtil<String, String> K_STRING_V_STRING = new MapUtil<String, String>(
			new SimpleKeyValueResolver<String, String>());

	public static final MapUtil<String, Set<String>> K_STRING_V_SET_E_STRING = new MapUtil<String, Set<String>>(
			new StringSetKeyValueResolver<String>(
					new SimpleEntryResolver<String>()));

	public MapUtil(KeyValueResolver<K, V> keyValueResolver) {
		this.keyValueResolver = keyValueResolver;
	}

	public Map<K, V> toMap(Object[] array) throws InvalidObjectTypeException {

		Map<K, V> map = new HashMap<K, V>();

		for (int index = 0; index < array.length; index++) {

			// Get key and value array
			Object[] keyAndValue = (Object[]) array[index];

			// Get key and value
			K topic = keyValueResolver.getKey(keyAndValue[0]);
			V topicType = keyValueResolver.getValue(keyAndValue[1]);

			map.put(topic, topicType);

		}

		return map;

	}

	public Object[] toObjects(Map<K, V> map) throws InvalidObjectTypeException {

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
			Object key = keyValueResolver.getKey(entry.getKey());
			Object value = keyValueResolver.getValue(entry.getValue());

			// Add to objects
			objects[index++] = new Object[] { key, value };

		}

		return objects;

	}

}
