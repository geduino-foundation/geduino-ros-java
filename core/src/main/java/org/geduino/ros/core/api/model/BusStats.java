package org.geduino.ros.core.api.model;

import java.io.Serializable;
import java.util.Set;

public class BusStats implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Set<PublisherStat> publisherStats;
	private final Set<SubscribeStat> subscribeStats;
	private final ServiceStat serviceStat;

	public BusStats(Set<PublisherStat> publisherStats,
			Set<SubscribeStat> subscribeStats, ServiceStat serviceStat) {

		this.publisherStats = publisherStats;
		this.subscribeStats = subscribeStats;
		this.serviceStat = serviceStat;

	}

	public Set<PublisherStat> getPublisherStats() {
		return publisherStats;
	}

	public Set<SubscribeStat> getSubscribeStats() {
		return subscribeStats;
	}

	public ServiceStat getServiceStat() {
		return serviceStat;
	}

	@Override
	public String toString() {
		return "BusStats [publisherStats=" + publisherStats
				+ ", subscribeStats=" + subscribeStats + ", serviceStat="
				+ serviceStat + "]";
	}

}
