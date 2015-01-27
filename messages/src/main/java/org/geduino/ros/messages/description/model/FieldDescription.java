package org.geduino.ros.messages.description.model;

import java.io.Serializable;

public class FieldDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	private final FieldType type;
	private final String name;
	private final boolean array;
	private final int size;
	private final String value;
	private final String comment;

	public FieldDescription(FieldType type, String name, boolean array,
			int size, String value, String comment) {

		this.type = type;
		this.name = name;
		this.array = array;
		this.size = size;
		this.value = value;
		this.comment = comment;

	}

	public FieldType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean isArray() {
		return array;
	}

	public int getSize() {
		return size;
	}

	public boolean isConstant() {

		// Get is constant
		boolean isConstant = value != null;

		return isConstant;

	}

	public String getValue() {
		return value;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FieldDescription other = (FieldDescription) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(type);

		if (array) {

			if (size != -1) {

				// Add array size
				stringBuffer.append("[").append(size).append("]");

			} else {

				// Add array without size
				stringBuffer.append("[]");

			}

		}

		// Add name and value if exists
		stringBuffer.append(" ").append(name)
				.append((value != null) ? "=".concat(value) : "");

		return stringBuffer.toString();

	}

}
