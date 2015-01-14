package org.geduino.ros.core.messages.model;

import org.geduino.ros.core.naming.model.MessageName;

public class MessageDetails<T> {

	private final MessageName messageName;
	private final String messageMd5sum;
	private final String messageDefinition;

	public MessageDetails(MessageName messageName, String messageMd5sum,
			String messageDefinition) {

		this.messageName = messageName;
		this.messageMd5sum = messageMd5sum;
		this.messageDefinition = messageDefinition;

	}

	public MessageName getMessageName() {
		return messageName;
	}

	public String getMessageMd5Sum() {
		return messageMd5sum;
	}

	public String getMessageDefinition() {
		return messageDefinition;
	}

}
