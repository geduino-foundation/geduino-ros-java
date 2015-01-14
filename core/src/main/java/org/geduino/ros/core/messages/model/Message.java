package org.geduino.ros.core.messages.model;

import java.util.HashMap;
import java.util.Map;

import org.geduino.ros.core.messages.exception.RosMessageRuntimeException;
import org.geduino.ros.core.messages.model.primitive.BoolFieldValue;
import org.geduino.ros.core.messages.model.primitive.Int16FieldValue;
import org.geduino.ros.core.messages.model.primitive.Int8FieldValue;
import org.geduino.ros.core.messages.model.primitive.StringFieldValue;
import org.geduino.ros.core.messages.model.primitive.UInt8FieldValue;

public class Message {

	private final Map<String, Field> fieldMap;

	public Message() {
		fieldMap = new HashMap<String, Field>();
	}

	protected BoolFieldValue getBoolFieldValue(String fieldName) {
		return (BoolFieldValue) getFieldValue(BoolFieldValue.class, fieldName);
	}

	protected Int16FieldValue getInt16FieldValue(String fieldName) {
		return (Int16FieldValue) getFieldValue(Int16FieldValue.class, fieldName);
	}

	protected Int8FieldValue getInt8FieldValue(String fieldName) {
		return (Int8FieldValue) getFieldValue(Int8FieldValue.class, fieldName);
	}

	protected UInt8FieldValue getUInt8FieldValue(String fieldName) {
		return (UInt8FieldValue) getFieldValue(UInt8FieldValue.class, fieldName);
	}

	protected StringFieldValue getStringFieldValue(String fieldName) {
		return (StringFieldValue) getFieldValue(StringFieldValue.class,
				fieldName);
	}

	private FieldValue getFieldValue(Class<?> expectedFieldValueClass,
			String fieldName) {

		// Get field value
		FieldValue fieldValue = getField(fieldName).getValue();

		if (fieldValue.getClass().isInstance(expectedFieldValueClass)) {
			return fieldValue;
		} else {

			// Throw exception
			throw new RosMessageRuntimeException("field: " + fieldName
					+ " expected class is: " + expectedFieldValueClass
					+ " but was: " + fieldValue.getClass());

		}

	}

	private Field getField(String fieldName) {

		// Get field
		Field field = fieldMap.get(fieldName);

		if (field != null) {
			return field;
		} else {

			// Throw exception
			throw new RosMessageRuntimeException("field does not exist: "
					+ fieldName);

		}

	}

}
