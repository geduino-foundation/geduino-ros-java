package org.geduino.ros.messages.generator;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.model.FieldDescription;
import org.geduino.ros.messages.description.model.FieldType;
import org.geduino.ros.messages.description.model.MessageDescription;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

class ClassGenerator {

	private static final Logger LOGGER = Logger.getLogger(ClassGenerator.class);

	private final JCodeModel jCodeModel;
	private final FullyQualifiedNameResolver fullyQualifiedNameResolver;
	private final DetailsFieldGenerator detailsFieldGenerator;

	ClassGenerator(JCodeModel jCodeModel,
			FullyQualifiedNameResolver fullyQualifiedNameResolver,
			DetailsFieldGenerator detailsFieldGenerator) {

		this.jCodeModel = jCodeModel;
		this.fullyQualifiedNameResolver = fullyQualifiedNameResolver;
		this.detailsFieldGenerator = detailsFieldGenerator;

	}

	void generate(MessageDescription messageDescription)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("generating message class: " + messageDescription + " ...");

		// Get message name
		MessageName messageName = messageDescription.getName();

		// Get class fully qualified name
		String classFullyQualifiedName = fullyQualifiedNameResolver
				.resolve(messageName);

		try {

			// Create class
			JDefinedClass jDefinedClass = jCodeModel
					._class(classFullyQualifiedName);
			jDefinedClass._extends(Message.class);
			jDefinedClass.javadoc().add(messageDescription.getComment());

			// Create details field
			detailsFieldGenerator.generate(messageDescription, jDefinedClass);

			// Create field, getLength, serialize and deserialize generator
			FieldGenerator fieldGenerator = new FieldGenerator(jCodeModel,
					jDefinedClass, fullyQualifiedNameResolver);
			GetLengthMethodGenerator getLengthMethodGenerator = new GetLengthMethodGenerator(
					jCodeModel, jDefinedClass);
			SerializeMethodGenerator serializeMethodGenerator = new SerializeMethodGenerator(
					jCodeModel, jDefinedClass);
			DeserializeMethodGenerator deserializeMethodGenerator = new DeserializeMethodGenerator(
					jCodeModel, jDefinedClass);

			for (Iterator<FieldDescription> iterator = messageDescription
					.getFieldDescriptions().iterator(); iterator.hasNext();) {

				// Get next field description
				FieldDescription fieldDescription = iterator.next();

				// Generate field
				JFieldVar jFieldVar = fieldGenerator.generate(fieldDescription);

				if (!fieldDescription.isConstant()) {

					// Get field type
					FieldType fieldType = fieldDescription.getType();

					// Generate getLength, serialize and deserialize method
					getLengthMethodGenerator.generate(fieldType, jFieldVar);
					serializeMethodGenerator.generate(fieldType, jFieldVar);
					deserializeMethodGenerator.generate(fieldType, jFieldVar);

				} else {

					// Log
					LOGGER.trace("skipping getLength(), serialize(DataWriter) and deserialize(DataReader) method generation for constant field: "
							+ fieldDescription.getName());

				}

			}

			// Generate return statement of getLength method
			getLengthMethodGenerator.generateReturnStatement();

		} catch (JClassAlreadyExistsException ex) {

			// Throw exception
			throw new RosMessageGeneratorException(
					"could not create message class: "
							+ classFullyQualifiedName, ex);

		}

	}

}
