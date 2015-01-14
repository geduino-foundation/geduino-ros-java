package org.geduino.ros.messages.description.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.geduino.ros.core.messages.model.FieldType;
import org.geduino.ros.core.messages.model.MessageFieldType;
import org.geduino.ros.core.messages.model.PrimitiveFieldType;
import org.geduino.ros.core.naming.model.BaseName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;

public class FieldDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Pattern FIELD_DESCRIPTION_PATTERN = Pattern
			.compile("^([a-zA-Z_][a-zA-Z0-9_]*)(\\[([0-9]+)?\\])? +([a-zA-Z0-9_]+)( +(= +((\\d|\".+\")+))?)?( +#.+)?");

	private static final Pattern PRIMITIVE_TYPE_PATTERN = Pattern
			.compile("(bool|int8|uint8|int16|uint16|int32|uint32|int64|uint64|float32|float64|string|byte|char|time|duration)");

	private final FieldType type;
	private final String name;
	private final boolean array;
	private final int size;
	private final String value;

	public static FieldDescription parseFieldDefinition(
			BaseName packageName, String string)
			throws RosMessageDescriptionException {

		// Get matcher for given string
		Matcher matcher = FIELD_DESCRIPTION_PATTERN.matcher(string);

		if (matcher.matches()) {

			// Get matched group
			String type = matcher.group(1);
			String array = matcher.group(2);
			String size = matcher.group(3);
			String name = matcher.group(4);
			String value = matcher.group(7);

			try {

				int sizeInt = -1;

				if (size != null) {

					// Parse size
					sizeInt = Integer.parseInt(size);

				}

				// Create field type
				FieldType fieldType = parseFieldType(packageName, type);

				// Create field definition
				FieldDescription fieldDefinition = new FieldDescription(
						fieldType, name, array != null, sizeInt, value);

				return fieldDefinition;

			} catch (NumberFormatException ex) {

				// Throw exception
				throw new RosMessageDescriptionException("invalid array size: "
						+ size, ex);

			}

		} else {

			// Throw exception
			throw new RosMessageDescriptionException(
					"malformed field definition: " + string);

		}

	}

	private static FieldType parseFieldType(BaseName packageName, String string)
			throws RosMessageDescriptionException {

		// Get matcher for given string
		Matcher matcher = PRIMITIVE_TYPE_PATTERN.matcher(string);

		if (matcher.matches()) {

			// Get primitive field type
			PrimitiveFieldType primitiveFieldType = PrimitiveFieldType
					.valueOf(string.toUpperCase());

			return primitiveFieldType;

		} else {

			// Create message name
			MessageName messageName = Name
					.parseMessageName(packageName, string);

			// Create message field type
			MessageFieldType messageFieldType = new MessageFieldType(
					messageName);

			return messageFieldType;

		}

	}

	public FieldDescription(FieldType type, String name, boolean array,
			int size, String value) {

		this.type = type;
		this.name = name;
		this.array = array;
		this.size = size;
		this.value = value;

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

	public String getValue() {
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

		// Get string
		String string = type.toString()
				.concat((size != -1) ? "[" + size + "]" : "").concat(" ")
				.concat(name);

		return string;

	}

}
