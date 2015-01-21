package org.geduino.ros.core.messages.model;

import java.util.HashMap;
import java.util.Map;

import org.geduino.ros.core.messages.exception.RosMessageRuntimeException;
import org.geduino.ros.core.messages.model.primitive.BoolFieldValue;
import org.geduino.ros.core.messages.model.primitive.Float32FieldValue;
import org.geduino.ros.core.messages.model.primitive.Float64FieldValue;
import org.geduino.ros.core.messages.model.primitive.Int16FieldValue;
import org.geduino.ros.core.messages.model.primitive.Int32FieldValue;
import org.geduino.ros.core.messages.model.primitive.Int64FieldValue;
import org.geduino.ros.core.messages.model.primitive.Int8FieldValue;
import org.geduino.ros.core.messages.model.primitive.StringFieldValue;
import org.geduino.ros.core.messages.model.primitive.UInt16FieldValue;
import org.geduino.ros.core.messages.model.primitive.UInt32FieldValue;
import org.geduino.ros.core.messages.model.primitive.UInt64FieldValue;
import org.geduino.ros.core.messages.model.primitive.UInt8FieldValue;

public class Message {

	private final Map<String, Field> fieldMap;

	public Message() {
		fieldMap = new HashMap<String, Field>();
	}
	
	protected void addField(String name, Field field) {
		fieldMap.put(name, field);
	}

	protected BoolFieldValue getBoolFieldValue(String fieldName) {
		return (BoolFieldValue) getFieldValue(BoolFieldValue.class, fieldName);
	}

	protected Int8FieldValue getInt8FieldValue(String fieldName) {
		return (Int8FieldValue) getFieldValue(Int8FieldValue.class, fieldName);
	}

	protected UInt8FieldValue getUInt8FieldValue(String fieldName) {
		return (UInt8FieldValue) getFieldValue(UInt8FieldValue.class, fieldName);
	}

	protected Int16FieldValue getInt16FieldValue(String fieldName) {
		return (Int16FieldValue) getFieldValue(Int16FieldValue.class, fieldName);
	}

	protected UInt16FieldValue getUInt16FieldValue(String fieldName) {
		return (UInt16FieldValue) getFieldValue(UInt16FieldValue.class,
				fieldName);
	}

	protected Int32FieldValue getInt32FieldValue(String fieldName) {
		return (Int32FieldValue) getFieldValue(Int32FieldValue.class, fieldName);
	}

	protected UInt32FieldValue getUInt32FieldValue(String fieldName) {
		return (UInt32FieldValue) getFieldValue(UInt32FieldValue.class,
				fieldName);
	}

	protected Int64FieldValue getInt64FieldValue(String fieldName) {
		return (Int64FieldValue) getFieldValue(Int64FieldValue.class, fieldName);
	}

	protected UInt64FieldValue getUInt64FieldValue(String fieldName) {
		return (UInt64FieldValue) getFieldValue(UInt64FieldValue.class,
				fieldName);
	}

	protected Float32FieldValue getFloat32FieldValue(String fieldName) {
		return (Float32FieldValue) getFieldValue(Float32FieldValue.class,
				fieldName);
	}

	protected Float64FieldValue getFloat64FieldValue(String fieldName) {
		return (Float64FieldValue) getFieldValue(Float64FieldValue.class,
				fieldName);
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
