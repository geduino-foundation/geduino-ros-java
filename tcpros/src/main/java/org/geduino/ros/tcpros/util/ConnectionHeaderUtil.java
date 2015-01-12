package org.geduino.ros.tcpros.util;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.tcpros.model.ConnectionHeader;

public class ConnectionHeaderUtil {

	public static int getHeaderLength(ConnectionHeader connectionHeader) {

		// Get field set
		Set<Map.Entry<String, String>> fieldSet = connectionHeader.fieldSet();
		
		int length = 0;

		for (Iterator<Map.Entry<String, String>> iterator = fieldSet.iterator(); iterator
				.hasNext();) {

			// Get next entry
			Map.Entry<String, String> field = iterator.next();

			// Increase length by field length and 4 bytes for field length header
			length += getFieldLength(field) + 4;

		}

		return length;

	}

	public static int getFieldLength(Map.Entry<String, String> field) {

		// Increase length by key and value length adding 1 byte for '=' sign
		int length = field.getKey().length() + field.getValue().length()
				+ 1;

		return length;

	}

}
