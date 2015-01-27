package org.geduino.ros.messages.generator;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataType;
import org.geduino.ros.core.messages.model.DataWriter;
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

class SerializeMethodGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(SerializeMethodGenerator.class);

	static void generate(JCodeModel jCodeModel, JDefinedClass jDefinedClass,
			List<FieldDescription> fieldDescriptions)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding serialize(DataWriter) method...");

		// Create serialize method
		JMethod serializeJMethod = jDefinedClass.method(JMod.PUBLIC,
				void.class, "serialize");
		JVar dataWriterJVar = serializeJMethod.param(
				jCodeModel.ref(DataWriter.class), "dataWriter");
		serializeJMethod._throws(IOException.class);
		serializeJMethod._throws(RosMessageSerializationException.class);
		serializeJMethod.annotate(Override.class);

		for (Iterator<FieldDescription> iterator = fieldDescriptions.iterator(); iterator
				.hasNext();) {

			// Get next field description
			FieldDescription fieldDescription = iterator.next();

			if (!fieldDescription.isConstant()) {

				// Generate
				generate(jCodeModel, serializeJMethod, dataWriterJVar,
						fieldDescription);

			}

		}

	}

	private static void generate(JCodeModel jCodeModel,
			JMethod serializeJMethod, JVar dataWriterJVar,
			FieldDescription fieldDescription)
			throws RosMessageGeneratorException {

		// Get reference to field variable
		JFieldRef jFieldRef = JExpr.ref(fieldDescription.getName());

		if (fieldDescription.isArray()) {

			// Write array length
			serializeJMethod.body().add(
					JExpr.invoke(dataWriterJVar, "writeUInt32").arg(
							jFieldRef.ref("length")));

			// Create write for loop
			JForLoop jForLoop = serializeJMethod.body()._for();
			JVar indexJVar = jForLoop.init(jCodeModel.INT, "index",
					JExpr.lit(0));
			jForLoop.test(indexJVar.lt(jFieldRef.ref("length")));
			jForLoop.update(indexJVar.incr());
			JExpression jExpression = jFieldRef.component(indexJVar);

			// Generate write statement
			generateWriteStatement(jCodeModel, jForLoop.body(), dataWriterJVar,
					fieldDescription.getType(), jExpression);

		} else {

			// Generate write statement
			generateWriteStatement(jCodeModel, serializeJMethod.body(),
					dataWriterJVar, fieldDescription.getType(), jFieldRef);

		}

	}

	private static void generateWriteStatement(JCodeModel jCodeModel,
			JBlock jBlock, JVar dataWriterJVar, FieldType fieldType,
			JExpression jExpression) throws RosMessageGeneratorException {

		if (fieldType instanceof PrimitiveFieldType) {

			// Cast to primitive field type
			PrimitiveFieldType primitiveFieldType = (PrimitiveFieldType) fieldType;

			// Generate write statement
			generateWriteStatement(jCodeModel, jBlock, dataWriterJVar,
					primitiveFieldType.getDataType(), jExpression);

		} else if (fieldType instanceof MessageFieldType) {

			// Generate write statement
			generateWriteStatement(jCodeModel, jBlock, dataWriterJVar,
					jExpression);

		} else {

			// Throw exception
			throw new RosMessageGeneratorException("unknown field type: "
					+ fieldType);

		}

	}

	private static void generateWriteStatement(JCodeModel jCodeModel,
			JBlock jBlock, JVar dataWriterJVar, DataType dataType,
			JExpression jExpression) throws RosMessageGeneratorException {

		switch (dataType) {

		case BOOL:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeBool").arg(
					jExpression));
			break;
		case INT8:
		case BYTE:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeInt8").arg(
					jExpression));
			break;
		case UINT8:
		case CHAR:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt8").arg(
					jExpression));
			break;
		case INT16:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeInt16").arg(
					jExpression));
			break;
		case UINT16:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt16").arg(
					jExpression));
			break;
		case INT32:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeInt32").arg(
					jExpression));
			break;
		case UINT32:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt32").arg(
					jExpression));
			break;
		case INT64:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeInt64").arg(
					jExpression));
			break;
		case UINT64:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt64").arg(
					jExpression));
			break;
		case FLOAT32:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeFloat32").arg(
					jExpression));
			break;
		case FLOAT64:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeFloat64").arg(
					jExpression));
			break;
		case STRING:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt32").arg(
					jExpression.invoke("length")));
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeString").arg(
					jExpression));
			break;
		case TIME:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeTime").arg(
					jExpression));
			break;
		case DURATION:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeDuration").arg(
					jExpression));
			break;

		default:

			// Throw exception
			throw new RosMessageGeneratorException("unknown data type: "
					+ dataType);

		}

	}

	private static void generateWriteStatement(JCodeModel jCodeModel,
			JBlock jBlock, JVar dataWriterJVar, JExpression jExpression) {

		// Add invocation of serialize method
		jBlock.add(JExpr.invoke(jExpression, "serialize").arg(dataWriterJVar));

	}

}
