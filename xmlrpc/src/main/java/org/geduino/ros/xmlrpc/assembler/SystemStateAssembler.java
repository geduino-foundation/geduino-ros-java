package org.geduino.ros.xmlrpc.assembler;

import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.core.api.model.SystemState;
import org.geduino.ros.core.naming.model.GlobalName;
import org.geduino.ros.xmlrpc.assembler.util.ObjectToMapAssembler;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class SystemStateAssembler {

	public static SystemState assemble(Object[] objects)
			throws InvalidObjectTypeException {

		if (objects.length == 3) {

			try {

				// Get publishers, subscribers and services
				Map<GlobalName, Set<URI>> publishers = ObjectToMapAssembler.K_GLOBAL_NAME_V_SET_URI
						.assemble((Object[]) objects[0]);
				Map<GlobalName, Set<URI>> subscribers = ObjectToMapAssembler.K_GLOBAL_NAME_V_SET_URI
						.assemble((Object[]) objects[1]);
				Map<GlobalName, Set<URI>> services = ObjectToMapAssembler.K_GLOBAL_NAME_V_SET_URI
						.assemble((Object[]) objects[2]);

				// Create system state
				SystemState systemState = new SystemState(publishers,
						subscribers, services);

				return systemState;

			} catch (ClassCastException ex) {

				// Throw exception
				throw new InvalidObjectTypeException("unexpected object type"
						+ objects.length);

			}

		} else {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object length: "
					+ objects.length);

		}

	}

}
