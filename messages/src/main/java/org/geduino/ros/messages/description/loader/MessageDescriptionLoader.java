package org.geduino.ros.messages.description.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.geduino.ros.core.naming.model.BaseName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;
import org.geduino.ros.messages.description.model.FieldDescription;
import org.geduino.ros.messages.description.model.MessageDescription;

class MessageDescriptionLoader {

	private static final Logger LOGGER = Logger
			.getLogger(MessageDescriptionLoader.class);

	static MessageDescription load(MessageName messageName,
			InputStream inputStream) throws IOException,
			RosMessageDescriptionException {

		List<FieldDescription> fieldDescriptions = new ArrayList<FieldDescription>();

		// Create input stream reader
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, Charset.forName("UTF-8"));

		// Create buffered reader
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		// Read raw message
		RawMessage rawMessage = readRawMessage(bufferedReader);

		String messageComment = "";

		for (Iterator<String> iterator = rawMessage.getLines().iterator(); iterator
				.hasNext();) {

			// Get next line
			String line = iterator.next();

			// Get next line
			if (line.startsWith("#")) {

				// Log
				LOGGER.trace("found comment: " + line + " ...");

				// Add to message comment (replacing # with blank space)
				messageComment = messageComment.concat(line.replace('#', ' '));

			} else {

				// Log
				LOGGER.trace("found field description: " + line + " ...");

				// Get package name
				BaseName packageName = messageName.getPackageName();

				// Parse field description
				FieldDescription fieldDescription = FieldDescriptionParser
						.parseFieldDefinition(packageName, line);

				fieldDescriptions.add(fieldDescription);

			}

		}

		// Create message description
		MessageDescription messageDescription = new MessageDescription(
				messageName, messageComment, fieldDescriptions);

		return messageDescription;

	}

	private static RawMessage readRawMessage(BufferedReader bufferedReader)
			throws IOException {

		RawMessage rawMessage = new RawMessage();

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {

			// Trim line
			String trimmedLine = line.trim();

			if (trimmedLine.startsWith("#")) {

				// Stage line
				rawMessage.stage(trimmedLine);

			} else if (trimmedLine.length() == 0) {
				// Discard line
			} else {

				// Commit and stage line
				rawMessage.commit();
				rawMessage.stage(trimmedLine);

			}

		}

		// Final commit
		rawMessage.commit();

		return rawMessage;

	}

}
