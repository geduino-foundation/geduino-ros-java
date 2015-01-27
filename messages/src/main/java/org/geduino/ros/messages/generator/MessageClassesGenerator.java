package org.geduino.ros.messages.generator;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.model.FieldDescription;
import org.geduino.ros.messages.description.model.FieldType;
import org.geduino.ros.messages.description.model.MessageDescription;
import org.geduino.ros.messages.description.model.MessageFieldType;
import org.geduino.ros.messages.description.model.PrimitiveFieldType;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;

class MessageClassesGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(MessageClassesGenerator.class);

	private final JCodeModel jCodeModel;
	private final String javaPackage;
	private final Map<MessageName, MessageDescription> messageDescriptionMap;

	MessageClassesGenerator(JCodeModel jCodeModel, String javaPackage,
			Map<MessageName, MessageDescription> messageDescriptionMap) {
		this.jCodeModel = jCodeModel;
		this.javaPackage = javaPackage;
		this.messageDescriptionMap = messageDescriptionMap;
	}

	void generate() throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("generating: " + messageDescriptionMap.size()
				+ " message classes in: " + javaPackage + " java package...");

		for (Iterator<MessageDescription> iterator = messageDescriptionMap
				.values().iterator(); iterator.hasNext();) {

			// Get next message description
			MessageDescription messageDescription = iterator.next();

			// Generate class
			generate(messageDescription);

		}

	}

	private void generate(MessageDescription messageDescription)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding message class: " + messageDescription + " ...");

		// Get message name
		MessageName messageName = messageDescription.getName();

		// Get class fully qualified name
		String classFullyQualifiedName = getClassFullyQualifiedName(messageName);

		try {

			// Create class
			JDefinedClass jDefinedClass = jCodeModel
					._class(classFullyQualifiedName);
			jDefinedClass._extends(Message.class);
			jDefinedClass.javadoc().add(messageDescription.getComment());

			// Add message details field, getLength, serialize and deserialize methods
			MessageDetailsFieldGenerator.generate(jCodeModel, jDefinedClass, messageDescription, messageDescriptionMap);
			GetLengthMethodGenerator.generate(jCodeModel, jDefinedClass,
					messageDescription.getFieldDescriptions());
			addDeerializeMethod(jDefinedClass);
			SerializeMethodGenerator.generate(jCodeModel, jDefinedClass,
					messageDescription.getFieldDescriptions());

			for (Iterator<FieldDescription> iterator = messageDescription
					.getFieldDescriptions().iterator(); iterator.hasNext();) {

				// Get next field description
				FieldDescription fieldDescription = iterator.next();

				// Add field
				addField(jDefinedClass, fieldDescription);

			}

		} catch (JClassAlreadyExistsException ex) {

			// Log
			LOGGER.error("could not create: " + classFullyQualifiedName, ex);

		}

	}

	private String getClassFullyQualifiedName(MessageName messageName) {

		// Get class fully qualified name
		String classFullyQualifiedName = javaPackage.concat(".")
				.concat(messageName.getPackageName().toString()).concat(".")
				.concat(messageName.getLastChild().toString());

		return classFullyQualifiedName;

	}

	private void addField(JDefinedClass jDefinedClass,
			FieldDescription fieldDescription)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding message field: " + fieldDescription + " ...");

		JType fieldJType = null;

		// Get field type
		FieldType fieldType = fieldDescription.getType();

		if (fieldType instanceof PrimitiveFieldType) {

			// Cast to primitive field type
			PrimitiveFieldType primitiveFieldType = (PrimitiveFieldType) fieldType;

			// Get java class
			Class<?> javaClass = primitiveFieldType.getDataType()
					.getJavaClass();

			if (javaClass.isPrimitive()) {

				// Get field jtype
				fieldJType = JType.parse(jCodeModel, javaClass.getName());

			} else {

				// Get field jclass
				fieldJType = jCodeModel.ref(javaClass.getName());

			}

		} else if (fieldType instanceof MessageFieldType) {

			// Cast to message field type
			MessageFieldType messageFieldType = (MessageFieldType) fieldType;

			// Get message name
			MessageName messageName = messageFieldType.getMessageName();

			// Get class fully qualified name
			String classFullyQualifiedName = getClassFullyQualifiedName(messageName);

			// Get jclass
			fieldJType = jCodeModel.ref(classFullyQualifiedName);

		} else {

			// Throw exception
			throw new RosMessageGeneratorException("unknow field type: "
					+ fieldType);

		}

		if (fieldDescription.isArray()) {

			// Get as array
			fieldJType = fieldJType.array();

		}

		JExpression initExpression = null;

		if (fieldJType.isArray() || !(fieldType instanceof PrimitiveFieldType)) {

			// Create init expression
			initExpression = JExpr._new(fieldJType);

		} else {

			// Create init expression
			initExpression = Constants.INIT_VALUE
					.get(((PrimitiveFieldType) fieldDescription.getType())
							.getDataType());

		}

		int mod = JMod.PUBLIC;

		if (fieldDescription.isConstant()) {

			mod = JMod.PUBLIC + JMod.STATIC + JMod.FINAL;

			initExpression = JExpr.direct(fieldDescription.getValue());

		}

		// Add field
		JFieldVar jFieldVar = jDefinedClass.field(mod, fieldJType,
				fieldDescription.getName(), initExpression);
		jFieldVar.javadoc().add(fieldDescription.getComment());

	}

	private void addDeerializeMethod(JDefinedClass jDefinedClass) {

		JClass dataReaderJClass = jCodeModel.ref(DataReader.class);

		JMethod serializeJMethod = jDefinedClass.method(JMod.PUBLIC,
				void.class, "deserialize");
		serializeJMethod.param(dataReaderJClass, "dataReader");
		serializeJMethod._throws(IOException.class);
		serializeJMethod._throws(RosMessageSerializationException.class);

	}

}
