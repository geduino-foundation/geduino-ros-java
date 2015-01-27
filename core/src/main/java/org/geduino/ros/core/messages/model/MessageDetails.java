package org.geduino.ros.core.messages.model;

import org.geduino.ros.core.naming.model.MessageName;

public class MessageDetails<T> {

	private final MessageName messageName;
	private final String messageMd5sum;
	private final String messageDefinition;
	private final Class<T> messageClass;

	public MessageDetails(MessageName messageName, String messageMd5sum,
			String messageDefinition, Class<T> messageClass) {

		this.messageName = messageName;
		this.messageMd5sum = messageMd5sum;
		this.messageDefinition = messageDefinition;
		this.messageClass = messageClass;

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

	public Class<T> getMessageClass() {
		return messageClass;
	}

}
