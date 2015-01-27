package org.geduino.ros.messages.generator;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.model.DataType;
import org.geduino.ros.messages.description.model.FieldDescription;
import org.geduino.ros.messages.description.model.FieldType;
import org.geduino.ros.messages.description.model.MessageFieldType;
import org.geduino.ros.messages.description.model.PrimitiveFieldType;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

class GetLengthMethodGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(GetLengthMethodGenerator.class);

	static void generate(JCodeModel jCodeModel, JDefinedClass jDefinedClass,
			List<FieldDescription> fieldDescriptions)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding getLength() method...");

		// Create get length method
		JMethod getLengthJMethod = jDefinedClass.method(JMod.PUBLIC,
				long.class, "getLength");
		getLengthJMethod.annotate(Override.class);

		// Init length var
		JVar lengthJVar = getLengthJMethod.body().decl(jCodeModel.LONG,
				"length", JExpr.lit(0));

		for (Iterator<FieldDescription> iterator = fieldDescriptions.iterator(); iterator
				.hasNext();) {

			// Get next field description
			FieldDescription fieldDescription = iterator.next();

			if (!fieldDescription.isConstant()) {

				// Generate field length statement
				generateFieldLengthStatement(jCodeModel,
						getLengthJMethod.body(), lengthJVar, fieldDescription);

			}

		}

		// Add return
		getLengthJMethod.body()._return(lengthJVar);

	}

	private static void generateFieldLengthStatement(JCodeModel jCodeModel,
			JBlock jBlock, JVar lengthJVar, FieldDescription fieldDescription)
			throws RosMessageGeneratorException {

		// Get reference to field variable
		JFieldRef jFieldRef = JExpr.ref(fieldDescription.getName());

		if (fieldDescription.isArray()) {

			// Create for loop
			jBlock.assignPlus(lengthJVar, JExpr.lit(4));
			JForLoop jForLoop = jBlock._for();
			JVar indexJVar = jForLoop.init(jCodeModel.INT, "index",
					JExpr.lit(0));
			jForLoop.test(indexJVar.lt(jFieldRef.ref("length")));
			jForLoop.update(indexJVar.incr());
			jForLoop.body()
					.assignPlus(
							lengthJVar,
							generateFieldLengthExpression(jCodeModel, jFieldRef.component(indexJVar),
									fieldDescription));

		} else {

			// Add field length statement
			jBlock.assignPlus(lengthJVar,
					generateFieldLengthExpression(jCodeModel, jFieldRef, fieldDescription));

		}

	}

	private static JExpression generateFieldLengthExpression(
			JCodeModel jCodeModel, JExpression toAddJExpression,
			FieldDescription fieldDescription)
			throws RosMessageGeneratorException {

		// Get field type
		FieldType fieldType = fieldDescription.getType();

		if (fieldType instanceof PrimitiveFieldType) {

			// Cast to primitive field type
			PrimitiveFieldType primitiveFieldType = (PrimitiveFieldType) fieldType;

			// Get data type
			DataType dataType = primitiveFieldType.getDataType();

			// Generate expression
			JExpression jExpression = generatePrimitiveTypeFieldLengthExpression(
					jCodeModel, dataType, toAddJExpression);

			return jExpression;

		} else if (fieldType instanceof MessageFieldType) {

			// Generate expression
			JExpression jExpression = generateMessageTypeFieldLengthExpression(
					jCodeModel, toAddJExpression);

			return jExpression;

		} else {

			// Throw exception
			throw new RosMessageGeneratorException("unknown field type: "
					+ fieldType);

		}

	}

	private static JExpression generatePrimitiveTypeFieldLengthExpression(
			JCodeModel jCodeModel, DataType dataType, JExpression toAddJExpression)
			throws RosMessageGeneratorException {

		if (DataType.STRING.equals(dataType)) {

			// Get expression
			JExpression jExpression = toAddJExpression.invoke("length").plus(JExpr.lit(4));

			return jExpression;

		} else {

			// Get expression
			JExpression jExpression = JExpr.lit(dataType.getLength());

			return jExpression;

		}

	}

	private static JExpression generateMessageTypeFieldLengthExpression(
			JCodeModel jCodeModel, JExpression toAddJExpression)
			throws RosMessageGeneratorException {

		// Get expression
		JExpression jExpression = toAddJExpression.invoke("getLength");

		return jExpression;

	}

}
