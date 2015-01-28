package org.geduino.ros.messages.generator;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.model.DataType;
import org.geduino.ros.messages.description.model.FieldType;
import org.geduino.ros.messages.description.model.MessageFieldType;
import org.geduino.ros.messages.description.model.PrimitiveFieldType;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

class GetLengthMethodGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(GetLengthMethodGenerator.class);

	private final JCodeModel jCodeModel;
	private final JMethod getLengthJMethod;
	private final JVar lengthJVar;

	GetLengthMethodGenerator(JCodeModel jCodeModel, JDefinedClass jDefinedClass) {

		this.jCodeModel = jCodeModel;

		// Log
		LOGGER.trace("adding getLength() method...");

		// Create get length method
		getLengthJMethod = jDefinedClass.method(JMod.PUBLIC, long.class,
				"getLength");
		getLengthJMethod.annotate(Override.class);

		// Init length var
		lengthJVar = getLengthJMethod.body().decl(jCodeModel.LONG, "length",
				JExpr.lit(0));

	}

	void generate(FieldType fieldType, JFieldVar jFieldVar)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding " + jFieldVar.name() + " length...");

		// Add non-javadoc comment
		getLengthJMethod.body().directStatement(
				"// Adding " + jFieldVar.name() + " length");

		if (jFieldVar.type().isArray()) {

			// Create for loop
			getLengthJMethod.body().assignPlus(lengthJVar, JExpr.lit(4));
			JForLoop jForLoop = getLengthJMethod.body()._for();
			JVar indexJVar = jForLoop.init(jCodeModel.INT, "index",
					JExpr.lit(0));
			jForLoop.test(indexJVar.lt(jFieldVar.ref("length")));
			jForLoop.update(indexJVar.incr());
			jForLoop.body().assignPlus(
					lengthJVar,
					getComponentLengthExpression(fieldType,
							jFieldVar.component(indexJVar)));

		} else {

			// Add field length statement
			getLengthJMethod.body().assignPlus(lengthJVar,
					getComponentLengthExpression(fieldType, jFieldVar));

		}

	}

	void generateReturnStatement() throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding return statement...");
				
		// Add return
		getLengthJMethod.body()._return(lengthJVar);
				
	}

	private JExpression getComponentLengthExpression(FieldType fieldType,
			JExpression componentJExpression)
			throws RosMessageGeneratorException {

		if (fieldType instanceof PrimitiveFieldType) {

			// Cast to primitive field type
			PrimitiveFieldType primitiveFieldType = (PrimitiveFieldType) fieldType;

			// Get data type
			DataType dataType = primitiveFieldType.getDataType();

			// Generate expression
			JExpression jExpression = getComponentLengthExpression(
					dataType, componentJExpression);

			return jExpression;

		} else if (fieldType instanceof MessageFieldType) {

			// Generate expression
			JExpression jExpression = getComponentLengthExpression(componentJExpression);

			return jExpression;

		} else {

			// Throw exception
			throw new RosMessageGeneratorException("unknown field type: "
					+ fieldType);

		}

	}

	private JExpression getComponentLengthExpression(DataType dataType,
			JExpression componentJExpression)
			throws RosMessageGeneratorException {

		if (DataType.STRING.equals(dataType)) {

			// Get expression
			JExpression jExpression = componentJExpression.invoke("length")
					.plus(JExpr.lit(4));

			return jExpression;

		} else {

			// Get expression
			JExpression jExpression = JExpr.lit(dataType.getLength());

			return jExpression;

		}

	}

	private JExpression getComponentLengthExpression(
			JExpression componentJExpression)
			throws RosMessageGeneratorException {

		// Get expression
		JExpression jExpression = componentJExpression.invoke("getLength");

		return jExpression;

	}

}
