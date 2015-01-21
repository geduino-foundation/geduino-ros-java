package org.geduino.ros.messages.description;

import org.geduino.ros.core.messages.model.Field;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.PrimitiveFieldType;
import org.geduino.ros.core.messages.model.primitive.StringFieldValue;

public class StringMessage extends Message {

	public StringMessage() {
		addField("data", new Field(PrimitiveFieldType.STRING, "data",
				new StringFieldValue()));
	}
	
	public String getData() {
		return getStringFieldValue("data").toString();
	}
	
	public void setData(String data) {
		getStringFieldValue("data").setStringValue(data);
	}

}
