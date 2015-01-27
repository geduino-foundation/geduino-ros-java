package org.geduino.ros.messages.description.loader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.geduino.ros.core.messages.model.DataType;
import org.geduino.ros.core.naming.model.BaseName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;
import org.geduino.ros.messages.description.model.FieldDescription;
import org.geduino.ros.messages.description.model.FieldType;
import org.geduino.ros.messages.description.model.MessageFieldType;
import org.geduino.ros.messages.description.model.PrimitiveFieldType;

class FieldDescriptionParser {

	private static final Pattern FIELD_DESCRIPTION_PATTERN = Pattern
			.compile("^([a-zA-Z_][a-zA-Z0-9_]*\\/?[a-zA-Z_][a-zA-Z0-9_]*)(\\[([0-9]+)?\\])? +([a-zA-Z0-9_]+)( *(= *((-?\\d|\".+\")+))?)?(( |\t)*#(.*))?");

	static FieldDescription parseFieldDefinition(BaseName packageName,
			String string) throws RosMessageDescriptionException {

		// Get matcher for given string
		Matcher matcher = FIELD_DESCRIPTION_PATTERN.matcher(string);

		if (matcher.matches()) {

			// Get matched group
			String type = matcher.group(1);
			String array = matcher.group(2);
			String size = matcher.group(3);
			String name = matcher.group(4);
			String value = matcher.group(7);
			String comment = matcher.group(11);

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
						fieldType, name, array != null, sizeInt, value,
						(comment != null) ? comment : "");

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

		try {

			// Get data type
			DataType dataType = DataType.valueOf(string.toUpperCase());

			// Create primitive field type
			PrimitiveFieldType primitiveFieldType = new PrimitiveFieldType(
					dataType);

			return primitiveFieldType;

		} catch (IllegalArgumentException ex) {

			if ("Header".equals(string)) {

				// Add std_msgs package name
				string = "std_msgs/Header";

			}

			// Create message name
			MessageName messageName = Name
					.parseMessageName(packageName, string);

			// Create message field type
			MessageFieldType messageFieldType = new MessageFieldType(
					messageName);

			return messageFieldType;

		}

	}

}
