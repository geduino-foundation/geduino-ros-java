package org.geduino.ros.xmlrpc.assembler;


import java.util.Iterator;
import java.util.Set;

import org.geduino.ros.core.api.model.BusStats;
import org.geduino.ros.core.api.model.PublisherConnectionData;
import org.geduino.ros.core.api.model.PublisherStat;
import org.geduino.ros.core.api.model.ServiceStat;
import org.geduino.ros.core.api.model.SubscribeStat;
import org.geduino.ros.core.api.model.SubscriberConnectionData;

public class BusStatsObjectsAssembler {

	public static Object[] assemble(BusStats busStats) {

		// Create objects
		Object[] objects = new Object[3];

		// Assemble
		objects[0] = assemblePublisherStats(busStats.getPublisherStats());
		objects[1] = assembleSubscriberStats(busStats.getSubscribeStats());
		objects[2] = assemble(busStats.getServiceStat());

		return objects;

	}

	private static Object[] assemblePublisherStats(Set<PublisherStat> publisherStats) {

		// Create objects
		Object[] objects = new Object[publisherStats.size()];

		int index = 0;

		for (Iterator<PublisherStat> iterator = publisherStats.iterator(); iterator
				.hasNext();) {

			// Get next publisher stat
			PublisherStat publisherStat = iterator.next();

			// Assemble
			objects[index++] = assemble(publisherStat);

		}

		return objects;

	}

	private static Object[] assembleSubscriberStats(Set<SubscribeStat> subscribeStats) {

		// Create objects
		Object[] objects = new Object[subscribeStats.size()];

		int index = 0;

		for (Iterator<SubscribeStat> iterator = subscribeStats.iterator(); iterator
				.hasNext();) {

			// Get next subscriber stat
			SubscribeStat subscriberStat = iterator.next();

			// Assemble
			objects[index++] = assemble(subscriberStat);

		}

		return objects;

	}

	private static Object[] assemble(PublisherStat publisherStat) {

		// Create objects
		Object[] objects = new Object[3];

		// Assemble
		objects[0] = publisherStat.getTopicName().toString();
		objects[1] = publisherStat.getMessageDataSent();
		objects[2] = assemblePublisherConnectionDatas(publisherStat
				.getPublisherConnectionDatas());

		return objects;

	}

	private static Object[] assemble(SubscribeStat subscribeStat) {

		// Create objects
		Object[] objects = new Object[2];

		// Assemble
		objects[0] = subscribeStat.getTopicName().toString();
		objects[1] = assembleSubscriberConnectionDatas(subscribeStat
				.getSubscriberConnectionDatas());

		return objects;

	}

	private static Object[] assemble(ServiceStat serviceStat) {

		// Create objects
		Object[] objects = new Object[3];

		// Assemble
		objects[0] = serviceStat.getNumRequest();
		objects[1] = serviceStat.getByteReceived();
		objects[2] = serviceStat.getByteSent();

		return objects;

	}

	private static Object[] assemblePublisherConnectionDatas(
			Set<PublisherConnectionData> publisherConnectionDatas) {

		// Create objects
		Object[] objects = new Object[publisherConnectionDatas.size()];

		int index = 0;

		for (Iterator<PublisherConnectionData> iterator = publisherConnectionDatas
				.iterator(); iterator.hasNext();) {

			// Get next publisher connection data
			PublisherConnectionData publisherConnectionData = iterator.next();

			// Assemble
			objects[index++] = assemble(publisherConnectionData);

		}

		return objects;

	}

	private static Object[] assembleSubscriberConnectionDatas(
			Set<SubscriberConnectionData> subscriberConnectionDatas) {

		// Create objects
		Object[] objects = new Object[subscriberConnectionDatas.size()];

		int index = 0;

		for (Iterator<SubscriberConnectionData> iterator = subscriberConnectionDatas
				.iterator(); iterator.hasNext();) {

			// Get next subscriber connection data
			SubscriberConnectionData subscriberConnectionData = iterator.next();

			// Assemble
			objects[index++] = assemble(subscriberConnectionData);

		}

		return objects;

	}

	private static Object[] assemble(PublisherConnectionData publisherConnectionData) {

		// Create objects
		Object[] objects = new Object[4];

		// Assemble
		objects[0] = publisherConnectionData.getConnectionId();
		objects[1] = publisherConnectionData.getByteSent();
		objects[2] = publisherConnectionData.getNumSent();
		objects[3] = publisherConnectionData.isConnected();

		return objects;

	}

	private static Object[] assemble(SubscriberConnectionData subscriberConnectionData) {

		// Create objects
		Object[] objects = new Object[4];

		// Assemble
		objects[0] = subscriberConnectionData.getConnectionId();
		objects[1] = subscriberConnectionData.getByteReceived();
		objects[2] = subscriberConnectionData.getDropEstimate();
		objects[3] = subscriberConnectionData.isConnected();

		return objects;

	}

}
