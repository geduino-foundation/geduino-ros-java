package org.geduino.ros.messages.generator;

import org.apache.log4j.Logger;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.model.FieldDescription;
import org.geduino.ros.messages.description.model.FieldType;
import org.geduino.ros.messages.description.model.MessageFieldType;
import org.geduino.ros.messages.description.model.PrimitiveFieldType;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;

class FieldGenerator {

	private static final Logger LOGGER = Logger.getLogger(FieldGenerator.class);

	private final JCodeModel jCodeModel;
	private final JDefinedClass jDefinedClass;
	private final FullyQualifiedNameResolver fullyQualifiedNameResolver;

	FieldGenerator(JCodeModel jCodeModel, JDefinedClass jDefinedClass,
			FullyQualifiedNameResolver fullyQualifiedNameResolver) {
		this.jCodeModel = jCodeModel;
		this.jDefinedClass = jDefinedClass;
		this.fullyQualifiedNameResolver = fullyQualifiedNameResolver;
	}

	JFieldVar generate(FieldDescription fieldDescription)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding " + fieldDescription.getName() + " field...");

		// Get field jtype, init jexpression and modifier
		JType fieldJType = getFieldType(fieldDescription.getType());
		
		if (fieldDescription.isArray()) {

			// Get as array
			fieldJType = fieldJType.array();

		}
		
		JExpression initJExpression = getInitExpression(fieldDescription,
				fieldJType);
		int modifier = getModifier(fieldDescription);

		// Add field and javadoc
		JFieldVar jFieldVar = jDefinedClass.field(modifier, fieldJType,
				fieldDescription.getName(), initJExpression);
		jFieldVar.javadoc().add(fieldDescription.getComment());

		return jFieldVar;

	}

	private JType getFieldType(FieldType fieldType)
			throws RosMessageGeneratorException {

		if (fieldType instanceof PrimitiveFieldType) {

			// Cast to primitive field type
			PrimitiveFieldType primitiveFieldType = (PrimitiveFieldType) fieldType;

			// Get java class
			Class<?> javaClass = primitiveFieldType.getDataType()
					.getJavaClass();

			if (javaClass.isPrimitive()) {

				// Get field jtype
				JType fieldJType = JType.parse(jCodeModel, javaClass.getName());

				return fieldJType;

			} else {

				// Get field jclass
				JType fieldJType = jCodeModel.ref(javaClass.getName());

				return fieldJType;

			}

		} else if (fieldType instanceof MessageFieldType) {

			// Cast to message field type
			MessageFieldType messageFieldType = (MessageFieldType) fieldType;

			// Get message name
			MessageName messageName = messageFieldType.getMessageName();

			// Get class fully qualified name
			String classFullyQualifiedName = fullyQualifiedNameResolver
					.resolve(messageName);

			// Get field jtype as jclass
			JType fieldJType = jCodeModel.ref(classFullyQualifiedName);

			return fieldJType;

		} else {

			// Throw exception
			throw new RosMessageGeneratorException("unknow field type: "
					+ fieldType);

		}

	}

	private JExpression getInitExpression(FieldDescription fieldDescription,
			JType fieldJType) {

		// Get field type
		FieldType fieldType = fieldDescription.getType();

		if (fieldDescription.isConstant()) {

			// Create init expression
			JExpression initExpression = JExpr.direct(fieldDescription
					.getValue());

			return initExpression;

		} else if (fieldDescription.isArray()
				|| !(fieldType instanceof PrimitiveFieldType)) {

			// Create init expression
			JExpression initExpression = JExpr._new(fieldJType);

			return initExpression;

		} else {

			// Cast to primitive field type
			PrimitiveFieldType primitiveFieldType = (PrimitiveFieldType) fieldType;

			// Create init expression
			JExpression initExpression = InitValue.INIT_VALUE
					.get(primitiveFieldType.getDataType());

			return initExpression;

		}

	}

	private int getModifier(FieldDescription fieldDescription) {

		// Standard modifier
		int modifier = JMod.PUBLIC;

		if (fieldDescription.isConstant()) {

			// Add static and final
			modifier += JMod.STATIC + JMod.FINAL;

		}

		return modifier;

	}

}
