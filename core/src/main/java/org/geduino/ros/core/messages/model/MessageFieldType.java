package org.geduino.ros.core.messages.model;

import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.naming.model.Name;

public class MessageFieldType implements FieldType {

	private static final long serialVersionUID = 1L;

	private final MessageName messageName;

	public MessageFieldType(MessageName messageName) {
		this.messageName = messageName;
	}

	public MessageName getMessageName() {
		return messageName;
	}
	
	@Override
	public Name getSimpleName() {
		return messageName.getLastChild();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messageName == null) ? 0 : messageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageFieldType other = (MessageFieldType) obj;
		if (messageName == null) {
			if (other.messageName != null)
				return false;
		} else if (!messageName.equals(other.messageName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return messageName.toString();
	}
	
}

