package org.geduino.ros.core.messages.model;

import java.io.Serializable;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;

public interface FieldValue extends Serializable {
	
	byte[] getBytes();
	
	void setBytes(byte[] bytes) throws RosMessageSerializeException;
	
}
