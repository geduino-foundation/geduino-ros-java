package org.geduino.ros.messages.description.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.geduino.ros.core.naming.model.BaseName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;
import org.geduino.ros.messages.description.model.FieldDescription;
import org.geduino.ros.messages.description.model.MessageDescription;

public class MessageDescriptionLoader {

	private static final Logger LOGGER = Logger
			.getLogger(MessageDescriptionLoader.class);

	public static MessageDescription load(MessageName messageName,
			InputStream inputStream) throws IOException,
			RosMessageDescriptionException {

		List<FieldDescription> fieldDescriptions = new ArrayList<FieldDescription>();

		// Create input stream reader
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, Charset.forName("UTF-8"));

		// Create buffered reader
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {

			// Trim line
			String trimmedLine = line.trim();

			if (!trimmedLine.startsWith("#") && trimmedLine.length() > 0) {

				// Get package name
				BaseName packageName = messageName.getPackageName();

				// Parse field description
				FieldDescription fieldDescription = FieldDescription
						.parseFieldDefinition(packageName, trimmedLine);

				// Log
				LOGGER.trace("found field description: " + line + " ...");

				fieldDescriptions.add(fieldDescription);

			}

		}

		// Create message description
		MessageDescription messageDescription = new MessageDescription(
				messageName, fieldDescriptions);

		return messageDescription;

	}

}
