package org.geduino.ros.xmlrpc.assembler.util;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.geduino.ros.core.api.model.Protocol;
import org.geduino.ros.core.api.model.ProtocolType;
import org.geduino.ros.xmlrpc.assembler.util.impl.CastObjectToEntryAssembler;
import org.geduino.ros.xmlrpc.assembler.util.impl.ObjectToProtocolTypeEntryAssembler;
import org.geduino.ros.xmlrpc.assembler.util.impl.URIObjectToEntryAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class ObjectToSetAssembler<E> {

	private final ObjectToEntryAssembler<E> objectToEntryAssembler;

	public static final ObjectToSetAssembler<ProtocolType> PROTOCOL_TYPE = new ObjectToSetAssembler<ProtocolType>(
			new ObjectToProtocolTypeEntryAssembler());

	public static final ObjectToSetAssembler<Protocol> PROTOCOL = new ObjectToSetAssembler<Protocol>(
			new CastObjectToEntryAssembler<Protocol>());

	public static final ObjectToSetAssembler<URI> URI = new ObjectToSetAssembler<URI>(
			new URIObjectToEntryAssembler());

	public ObjectToSetAssembler(ObjectToEntryAssembler<E> objectToEntryAssembler) {
		this.objectToEntryAssembler = objectToEntryAssembler;
	}

	public Set<E> assemble(Object[] array) throws InvalidObjectTypeException {

		Set<E> set = new HashSet<E>();

		for (int index = 0; index < array.length; index++) {

			// Get entry
			E entry = objectToEntryAssembler.assemble(array[index]);

			set.add(entry);

		}

		return set;

	}

}
