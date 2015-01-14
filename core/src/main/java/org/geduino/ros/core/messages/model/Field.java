package org.geduino.ros.core.messages.model;

public class Field {

	private final FieldType type;
	private final String name;
	private final FieldValue value;

	public Field(FieldType type, String name, FieldValue value) {

		this.type = type;
		this.name = name;
		this.value = value;

	}

	public FieldType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public FieldValue getValue() {
		return value;
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
		Field other = (Field) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {

		// Get string
		String string = type.toString().concat(" ").concat(name).concat(" = ")
				.concat(value.toString());

		return string;

	}

}
