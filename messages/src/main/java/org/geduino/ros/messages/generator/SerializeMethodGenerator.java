package org.geduino.ros.messages.generator;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataType;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.messages.description.model.FieldType;
import org.geduino.ros.messages.description.model.MessageFieldType;
import org.geduino.ros.messages.description.model.PrimitiveFieldType;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

class SerializeMethodGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(SerializeMethodGenerator.class);

	private final JCodeModel jCodeModel;
	private final JMethod serializeJMethod;
	private final JVar dataWriterJVar;

	SerializeMethodGenerator(JCodeModel jCodeModel, JDefinedClass jDefinedClass) {

		this.jCodeModel = jCodeModel;

		// Log
		LOGGER.trace("adding serialize(DataWriter) method...");

		// Create serialize method
		serializeJMethod = jDefinedClass.method(JMod.PUBLIC, void.class,
				"serialize");
		dataWriterJVar = serializeJMethod.param(
				jCodeModel.ref(DataWriter.class), "dataWriter");
		serializeJMethod._throws(IOException.class);
		serializeJMethod._throws(RosMessageSerializationException.class);
		serializeJMethod.annotate(Override.class);
	}

	void generate(FieldType fieldType, JFieldVar jFieldVar)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding " + jFieldVar.name() + " serialization...");

		// Add non-javadoc comment
		serializeJMethod.body().directStatement(
				"// Serialize " + jFieldVar.name());

		if (jFieldVar.type().isArray()) {

			// Write array length
			serializeJMethod.body().add(
					JExpr.invoke(dataWriterJVar, "writeUInt32").arg(
							jFieldVar.ref("length")));

			// Create write for loop
			JForLoop jForLoop = serializeJMethod.body()._for();
			JVar indexJVar = jForLoop.init(jCodeModel.INT, "index",
					JExpr.lit(0));
			jForLoop.test(indexJVar.lt(jFieldVar.ref("length")));
			jForLoop.update(indexJVar.incr());
			JExpression componentJExpression = jFieldVar.component(indexJVar);

			// Generate write statement
			generateWriteStatement(fieldType, componentJExpression,
					jForLoop.body());

		} else {

			// Generate write statement
			generateWriteStatement(fieldType, jFieldVar,
					serializeJMethod.body());

		}

	}

	private void generateWriteStatement(FieldType fieldType,
			JExpression componentJExpression, JBlock jBlock)
			throws RosMessageGeneratorException {

		if (fieldType instanceof PrimitiveFieldType) {

			// Cast to primitive field type
			PrimitiveFieldType primitiveFieldType = (PrimitiveFieldType) fieldType;

			// Generate write statement
			generateWriteStatement(primitiveFieldType.getDataType(),
					componentJExpression, jBlock);

		} else if (fieldType instanceof MessageFieldType) {

			// Generate write statement
			generateWriteStatement(componentJExpression, jBlock);

		} else {

			// Throw exception
			throw new RosMessageGeneratorException("unknown field type: "
					+ fieldType);

		}

	}

	private void generateWriteStatement(DataType dataType,
			JExpression componentJExpression, JBlock jBlock)
			throws RosMessageGeneratorException {

		switch (dataType) {

		case BOOL:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeBool").arg(
					componentJExpression));
			break;
		case INT8:
		case BYTE:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeInt8").arg(
					componentJExpression));
			break;
		case UINT8:
		case CHAR:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt8").arg(
					componentJExpression));
			break;
		case INT16:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeInt16").arg(
					componentJExpression));
			break;
		case UINT16:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt16").arg(
					componentJExpression));
			break;
		case INT32:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeInt32").arg(
					componentJExpression));
			break;
		case UINT32:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt32").arg(
					componentJExpression));
			break;
		case INT64:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeInt64").arg(
					componentJExpression));
			break;
		case UINT64:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt64").arg(
					componentJExpression));
			break;
		case FLOAT32:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeFloat32").arg(
					componentJExpression));
			break;
		case FLOAT64:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeFloat64").arg(
					componentJExpression));
			break;
		case STRING:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeUInt32").arg(
					componentJExpression.invoke("length")));
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeString").arg(
					componentJExpression));
			break;
		case TIME:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeTime").arg(
					componentJExpression));
			break;
		case DURATION:
			jBlock.add(JExpr.invoke(dataWriterJVar, "writeDuration").arg(
					componentJExpression));
			break;

		default:

			// Throw exception
			throw new RosMessageGeneratorException("unknown data type: "
					+ dataType);

		}

	}

	private void generateWriteStatement(JExpression componentJExpression,
			JBlock jBlock) {

		// Add invocation of serialize method
		jBlock.add(JExpr.invoke(componentJExpression, "serialize").arg(
				dataWriterJVar));

	}

}
