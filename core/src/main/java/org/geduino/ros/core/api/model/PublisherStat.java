package org.geduino.ros.core.api.model;


import java.io.Serializable;
import java.util.Set;

import org.geduino.ros.core.naming.model.GlobalName;


public class PublisherStat implements Serializable {

	private static final long serialVersionUID = 1L;

	private final GlobalName topicName;
	private final int messageDataSent;
	private final Set<PublisherConnectionData> publisherConnectionDatas;

	public PublisherStat(GlobalName topicName, int messageDataSent,
			Set<PublisherConnectionData> publisherConnectionDatas) {

		this.topicName = topicName;
		this.messageDataSent = messageDataSent;
		this.publisherConnectionDatas = publisherConnectionDatas;

	}

	public GlobalName getTopicName() {
		return topicName;
	}

	public int getMessageDataSent() {
		return messageDataSent;
	}

	public Set<PublisherConnectionData> getPublisherConnectionDatas() {
		return publisherConnectionDatas;
	}

	@Override
	public String toString() {
		return "PublishStats [topicName=" + topicName + ", messageDataSent="
				+ messageDataSent + ", publisherConnectionDatas="
				+ publisherConnectionDatas + "]";
	}

}
