package org.geduino.ros.messages.generator;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.model.MessageDescription;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JCodeModel;

public class ClassesGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(ClassesGenerator.class);

	public static JCodeModel generate(
			Map<MessageName, MessageDescription> messageDescriptionMap,
			String javaPackage) throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("generating: " + messageDescriptionMap.size()
				+ " message classe(s) in package: " + javaPackage + " ...");

		// Create code model
		JCodeModel jCodeModel = new JCodeModel();

		// Create fuully qualified name resolver
		FullyQualifiedNameResolver fullyQualifiedNameResolver = new FullyQualifiedNameResolver(
				javaPackage);

		// Create md5 generator
		MD5Generator md5Generator = new MD5Generator(messageDescriptionMap);

		// Create details field generator
		DetailsFieldGenerator detailsFieldGenerator = new DetailsFieldGenerator(
				jCodeModel, md5Generator);

		// Create class generator
		ClassGenerator classGenerator = new ClassGenerator(jCodeModel,
				fullyQualifiedNameResolver, detailsFieldGenerator);

		for (Iterator<MessageDescription> iterator = messageDescriptionMap
				.values().iterator(); iterator.hasNext();) {

			// Get next message description
			MessageDescription messageDescription = iterator.next();

			// Generate class
			classGenerator.generate(messageDescription);

		}

		return jCodeModel;

	}

}
