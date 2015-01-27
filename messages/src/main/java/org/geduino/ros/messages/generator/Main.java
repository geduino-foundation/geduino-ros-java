package org.geduino.ros.messages.generator;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;
import org.geduino.ros.messages.description.loader.MessageDescriptionsLoader;
import org.geduino.ros.messages.description.model.MessageDescription;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.writer.FileCodeWriter;

public class Main {

	public static void main(String[] args) throws RosMessageGeneratorException,
			RosMessageDescriptionException, IOException {

		if (args.length != 3) {

			// Print usage
			System.out
					.println("Usage: java -jar messages-X.Y.Z <msg source folder> <msg target folder> <msg class package>");

			// Throw exception
			throw new RosMessageGeneratorException("invalid arguments length");

		}

		// Get message source folder, message target folder and java package
		File messageSourceFolder = new File(args[0]);
		File messageTargetFolder = new File(args[1]);
		String javaPackage = args[2];

		// Print
		System.out.println("loading messages from: "
				+ messageSourceFolder.getPath() + "...");

		// Load messages
		Map<MessageName, MessageDescription> messageDescriptionMap = loadMessages(messageSourceFolder);

		// Print
		System.out.println("validating dependency for: "
				+ messageDescriptionMap.size() + " messages...");

		// Validate dependencies
		validateDependencies(messageDescriptionMap);

		// Print
		System.out.println("generating messages classes in: "
				+ messageTargetFolder.getPath() + " with package: "
				+ javaPackage + " ...");

		// Generate message classes
		generateMessageClasses(messageDescriptionMap, javaPackage,
				messageTargetFolder);

		// Print
		System.out.println("done");

	}

	private static Map<MessageName, MessageDescription> loadMessages(
			File messageSourceFolder) throws RosMessageDescriptionException,
			IOException {

		// Load message description
		Map<MessageName, MessageDescription> messageDescriptionMap = MessageDescriptionsLoader
				.load(messageSourceFolder);

		return messageDescriptionMap;

	}

	private static void validateDependencies(
			Map<MessageName, MessageDescription> messageDescriptionMap)
			throws RosMessageGeneratorException {

		// Get message descriptions
		Collection<MessageDescription> messageDescriptions = messageDescriptionMap
				.values();

		for (Iterator<MessageDescription> iterator = messageDescriptions
				.iterator(); iterator.hasNext();) {

			// Get next message description
			MessageDescription messageDescription = iterator.next();

			// Get unmeet dependencies
			Set<MessageName> unmeetDependencies = messageDescription
					.getUnmeetDependencies(messageDescriptionMap.keySet());

			if (unmeetDependencies.size() > 0) {

				// Throw exception
				throw new RosMessageGeneratorException("message: "
						+ messageDescription.getName()
						+ " has unmeet dependencies: " + unmeetDependencies);

			}

		}

	}

	private static void generateMessageClasses(
			Map<MessageName, MessageDescription> messageDescriptionMap,
			String javaPackage, File messageTargetFolder)
			throws RosMessageGeneratorException, IOException {

		// Create code model
		JCodeModel jCodeModel = new JCodeModel();

		// Create message classes generator
		MessageClassesGenerator messageClassesGenerator = new MessageClassesGenerator(
				jCodeModel, javaPackage, messageDescriptionMap);

		// Generate message classes
		messageClassesGenerator.generate();

		if (!messageTargetFolder.exists()) {

			// Create message target folder
			messageTargetFolder.mkdirs();

		}

		// Create file code writer
		FileCodeWriter fileCodeWriter = new FileCodeWriter(messageTargetFolder);

		// Build classes
		jCodeModel.build(fileCodeWriter);

	}

}
