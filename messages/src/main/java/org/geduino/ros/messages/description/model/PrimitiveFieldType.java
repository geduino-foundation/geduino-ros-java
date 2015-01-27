package org.geduino.ros.messages.description.model;

import org.geduino.ros.core.messages.model.DataType;

public class PrimitiveFieldType implements FieldType {

	private static final long serialVersionUID = 1L;

	private final DataType dataType;

	public PrimitiveFieldType(DataType dataType) {
		this.dataType = dataType;
	}

	public DataType getDataType() {
		return dataType;
	}

	@Override
	public String toString() {
		return dataType.toString();
	}

}
