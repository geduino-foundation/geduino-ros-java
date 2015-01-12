package org.geduino.ros.xmlrpc.util;


import java.util.Iterator;
import java.util.Set;

import org.geduino.ros.core.api.model.BusInfo;

public class BusInfoObjectsAssembler {

	public static Object[] assemble(Set<BusInfo> busInfos) {

		// Create objects
		Object[] objects = new Object[busInfos.size()];

		int index = 0;

		for (Iterator<BusInfo> iterator = busInfos.iterator(); iterator
				.hasNext();) {

			// Get next bus info
			BusInfo busInfo = iterator.next();

			// Assemble
			objects[index++] = assemble(busInfo);

		}

		return objects;

	}

	private static Object[] assemble(BusInfo busInfo) {

		// Create objects
		Object[] objects = new Object[6];

		// Assemble
		objects[0] = busInfo.getConnectionId();
		objects[1] = busInfo.getDestinationId().toString();

		switch (busInfo.getDirection()) {

		case IN:
			objects[2] = "i";
			break;

		case OUT:
			objects[2] = "o";
			break;

		case BOTH:
			objects[2] = "b";
			break;

		}

		objects[3] = busInfo.getTransport().toString();
		objects[4] = busInfo.getTopicName();
		objects[5] = busInfo.isConnected();

		return objects;

	}

}
