package org.geduino.ros.messages.description.loader;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.geduino.ros.core.naming.model.BaseName;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;
import org.geduino.ros.messages.description.model.MessageDescription;

public class MessageDescriptionsLoader {

	private static final Logger LOGGER = Logger
			.getLogger(MessageDescriptionsLoader.class);

	private static final FileFilter ROS_MESSAGE_FOLDER_FILE_FILTER = new RosMessageFolderFileFilter();

	private static final FileFilter ROS_MESSAGE_FILE_FILTER = new RosMessageFileFilter();

	public static Map<MessageName, MessageDescription> load(File messageFolder)
			throws RosMessageDescriptionException, IOException {

		Map<MessageName, MessageDescription> messageDescriptions = new HashMap<MessageName, MessageDescription>();

		// Log
		LOGGER.trace("loading message description from message base folder: "
				+ messageFolder.getPath() + " ...");

		// Get message sub folders
		File[] messageSubFolders = messageFolder
				.listFiles(ROS_MESSAGE_FOLDER_FILE_FILTER);

		for (int index = 0; index < messageSubFolders.length; index++) {

			// Get next file
			File messageSubFolder = messageSubFolders[index];

			// Get package name
			BaseName packageName = (BaseName) Name.parseName(messageSubFolder
					.getName());

			// Load message description
			Map<MessageName, MessageDescription> loadedMessageDefinitions = load(
					packageName, messageSubFolder);

			// Add to message definitions
			messageDescriptions.putAll(loadedMessageDefinitions);

		}

		return messageDescriptions;

	}

	private static Map<MessageName, MessageDescription> load(
			BaseName packageName, File messageFolder)
			throws RosMessageDescriptionException, IOException {

		Map<MessageName, MessageDescription> messageDescriptions = new HashMap<MessageName, MessageDescription>();

		// Log
		LOGGER.trace("loading message description from message folder: "
				+ messageFolder.getPath() + " ...");

		// Get message files
		File[] messageFiles = messageFolder.listFiles(ROS_MESSAGE_FILE_FILTER);

		for (int index = 0; index < messageFiles.length; index++) {

			// Get next file
			File messageFile = messageFiles[index];

			// Get stripped file name
			String fileName = messageFile.getName();
			String strippedFileName = fileName.substring(0,
					fileName.length() - 4);

			// Get message name
			MessageName messageName = Name.parseMessageName(packageName,
					strippedFileName);

			// Load message definition
			MessageDescription messageDefinition = load(messageName,
					messageFile);

			// Add to message definitions
			messageDescriptions.put(messageName, messageDefinition);

		}

		return messageDescriptions;

	}

	private static MessageDescription load(MessageName messageName,
			File messageFile) throws RosMessageDescriptionException,
			IOException {

		// Log
		LOGGER.trace("loading message description: " + messageName
				+ " from file: " + messageFile.getPath() + " ...");

		InputStream inputStream = null;

		try {

			// Get file input stream
			inputStream = new FileInputStream(messageFile);

			// Load message description
			MessageDescription messageDescription = MessageDescriptionLoader
					.load(messageName, inputStream);

			return messageDescription;

		} finally {

			if (inputStream != null) {

				try {

					// Close input stream
					inputStream.close();

				} catch (Exception ex) {
				}

			}

		}

	}
}
