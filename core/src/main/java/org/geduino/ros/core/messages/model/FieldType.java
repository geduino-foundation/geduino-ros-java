package org.geduino.ros.core.messages.model;

import java.io.Serializable;

import org.geduino.ros.core.naming.model.Name;

public interface FieldType extends Serializable {
	
	Name getSimpleName();
	
}
