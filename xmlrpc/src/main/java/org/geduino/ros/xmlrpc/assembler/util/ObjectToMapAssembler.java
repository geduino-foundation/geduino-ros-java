package org.geduino.ros.xmlrpc.assembler.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.xmlrpc.assembler.util.impl.CastObjectToMapEntryAssembler;
import org.geduino.ros.xmlrpc.assembler.util.impl.GlobalNameMessageNameToMapEntryAssembler;
import org.geduino.ros.xmlrpc.assembler.util.impl.GlobalNameSetURIToMapEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ObjectToMapAssembler<K, V> {

	private ObjectToMapEntryAssembler<K, V> objectToMapEntryAssembler;

	public static final ObjectToMapAssembler<String, String> K_STRING_V_STRING = new ObjectToMapAssembler<String, String>(
			new CastObjectToMapEntryAssembler<String, String>());

	public static final ObjectToMapAssembler<GlobalName, MessageName> K_GLOBAL_NAME_V_MESSAGE_NAME = new ObjectToMapAssembler<GlobalName, MessageName>(
			new GlobalNameMessageNameToMapEntryAssembler());
	
	public static final ObjectToMapAssembler<GlobalName, Set<URI>> K_GLOBAL_NAME_V_SET_URI = new ObjectToMapAssembler<GlobalName, Set<URI>>(
			new GlobalNameSetURIToMapEntryAssembler());

	public ObjectToMapAssembler(
			ObjectToMapEntryAssembler<K, V> objectToMapEntryAssembler) {
		this.objectToMapEntryAssembler = objectToMapEntryAssembler;
	}

	public Map<K, V> assemble(Object[] objects)
			throws InvalidObjectTypeException {

		Map<K, V> map = new HashMap<K, V>();

		for (int index = 0; index < objects.length; index++) {

			// Get key and value array
			Object[] keyAndValue = (Object[]) objects[index];

			// Get key and value
			K topic = objectToMapEntryAssembler.assembleKey(keyAndValue[0]);
			V topicType = objectToMapEntryAssembler
					.assembleValue(keyAndValue[1]);

			map.put(topic, topicType);

		}

		return map;

	}

}
