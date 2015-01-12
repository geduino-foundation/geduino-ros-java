package org.geduino.ros.core.api.model;

import java.io.Serializable;
import java.util.Set;

public class SubscribeStat implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String topicName;
	private final Set<SubscriberConnectionData> subscriberConnectionDatas;

	public SubscribeStat(String topicName,
			Set<SubscriberConnectionData> subscriberConnectionDatas) {

		this.topicName = topicName;
		this.subscriberConnectionDatas = subscriberConnectionDatas;

	}

	public String getTopicName() {
		return topicName;
	}

	public Set<SubscriberConnectionData> getSubscriberConnectionDatas() {
		return subscriberConnectionDatas;
	}

	@Override
	public String toString() {
		return "SubscribeStats [topicName=" + topicName
				+ ", subscriberConnectionDatas=" + subscriberConnectionDatas
				+ "]";
	}

}
