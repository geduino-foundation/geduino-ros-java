package org.geduino.ros.core.messages.model.primitive;

import org.geduino.ros.core.messages.exception.RosMessageSerializeException;
import org.geduino.ros.core.messages.model.FieldValue;

public class StringFieldValue implements FieldValue {

	private static final long serialVersionUID = 1L;

	private String string;

	public StringFieldValue() {
		this.string = "";
	}

	public String getStringValue() {
		return string;
	}

	public void setStringValue(String string) {
		this.string = string;
	}

	@Override
	public byte[] getBytes() {

		// Get bytes
		byte[] bytes = string.getBytes();

		return bytes;

	}

	@Override
	public void setBytes(byte[] bytes) throws RosMessageSerializeException {

		// Get string
		string = new String(bytes);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
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
		StringFieldValue other = (StringFieldValue) obj;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return string;
	}

}
