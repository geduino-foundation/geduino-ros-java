package org.geduino.ros.messages.generator;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataType;
import org.geduino.ros.messages.description.model.FieldType;
import org.geduino.ros.messages.description.model.MessageFieldType;
import org.geduino.ros.messages.description.model.PrimitiveFieldType;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JAssignmentTarget;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

class DeserializeMethodGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(DeserializeMethodGenerator.class);

	private final JCodeModel jCodeModel;
	private final JMethod deserializeJMethod;
	private final JVar dataReaderJVar;

	DeserializeMethodGenerator(JCodeModel jCodeModel,
			JDefinedClass jDefinedClass) {

		this.jCodeModel = jCodeModel;

		// Log
		LOGGER.trace("adding deserialize(DataReader) method...");

		// Create deserialize method
		deserializeJMethod = jDefinedClass.method(JMod.PUBLIC, void.class,
				"deserialize");
		dataReaderJVar = deserializeJMethod.param(
				jCodeModel.ref(DataReader.class), "dataReder");
		deserializeJMethod._throws(IOException.class);
		deserializeJMethod._throws(RosMessageSerializationException.class);
		deserializeJMethod.annotate(Override.class);

	}

	void generate(FieldType fieldType, JFieldVar jFieldVar)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding " + jFieldVar.name() + " deserialization...");

		// Add non-javadoc comment
		deserializeJMethod.body().directStatement(
				"// Deserialize " + jFieldVar.name());

		if (jFieldVar.type().isArray()) {

			// Get array length
			JVar arrayLengthJVar = deserializeJMethod
					.body()
					.decl(jCodeModel.INT, jFieldVar.name().concat("Length"))
					.init(JExpr.cast(jCodeModel.INT,
							dataReaderJVar.invoke("readUInt32")));

			// Assign array with right size to field
			deserializeJMethod.body().assign(
					jFieldVar,
					JExpr.newArray(jFieldVar.type().elementType(),
							arrayLengthJVar));

			// Create write for loop
			JForLoop jForLoop = deserializeJMethod.body()._for();
			JVar indexJVar = jForLoop.init(jCodeModel.INT, "index",
					JExpr.lit(0));
			jForLoop.test(indexJVar.lt(arrayLengthJVar));
			jForLoop.update(indexJVar.incr());
			JAssignmentTarget jAssignmentTarget = jFieldVar
					.component(indexJVar);

			if (!(fieldType instanceof PrimitiveFieldType)) {

				// Add constructor for non primitive array element
				jForLoop.body().assign(jAssignmentTarget,
						JExpr._new(jFieldVar.type().elementType()));

			}

			// Generate read statement
			generateReadStatement(fieldType, jAssignmentTarget, jForLoop.body());

		} else {

			// Generate write statement
			generateReadStatement(fieldType, jFieldVar,
					deserializeJMethod.body());

		}

	}

	private void generateReadStatement(FieldType fieldType,
			JAssignmentTarget jAssignmentTarget, JBlock jBlock)
			throws RosMessageGeneratorException {

		if (fieldType instanceof PrimitiveFieldType) {

			// Cast to primitive field type
			PrimitiveFieldType primitiveFieldType = (PrimitiveFieldType) fieldType;

			// Generate write statement
			generateReadStatement(primitiveFieldType.getDataType(),
					jAssignmentTarget, jBlock);

		} else if (fieldType instanceof MessageFieldType) {

			// Generate write statement
			generateReadStatement(jAssignmentTarget, jBlock);

		} else {

			// Throw exception
			throw new RosMessageGeneratorException("unknown field type: "
					+ fieldType);

		}

	}

	private void generateReadStatement(DataType dataType,
			JAssignmentTarget jAssignmentTarget, JBlock jBlock)
			throws RosMessageGeneratorException {

		switch (dataType) {

		case BOOL:
			jBlock.assign(jAssignmentTarget, dataReaderJVar.invoke("readBool"));
			break;
		case INT8:
		case BYTE:
			jBlock.assign(jAssignmentTarget, dataReaderJVar.invoke("readInt8"));
			break;
		case UINT8:
		case CHAR:
			jBlock.assign(jAssignmentTarget, dataReaderJVar.invoke("readUInt8"));
			break;
		case INT16:
			jBlock.assign(jAssignmentTarget, dataReaderJVar.invoke("readInt16"));
			break;
		case UINT16:
			jBlock.assign(jAssignmentTarget,
					dataReaderJVar.invoke("readUInt16"));
			break;
		case INT32:
			jBlock.assign(jAssignmentTarget, dataReaderJVar.invoke("readInt32"));
			break;
		case UINT32:
			jBlock.assign(jAssignmentTarget,
					dataReaderJVar.invoke("readUInt32"));
			break;
		case INT64:
			jBlock.assign(jAssignmentTarget, dataReaderJVar.invoke("readInt64"));
			break;
		case UINT64:
			jBlock.assign(jAssignmentTarget,
					dataReaderJVar.invoke("readUInt64"));
			break;
		case FLOAT32:
			jBlock.assign(jAssignmentTarget,
					dataReaderJVar.invoke("readFloat32"));
			break;
		case FLOAT64:
			jBlock.assign(jAssignmentTarget,
					dataReaderJVar.invoke("readFloat64"));
			break;
		case STRING:
			jBlock.assign(
					jAssignmentTarget,
					dataReaderJVar.invoke("readString").arg(
							JExpr.cast(jCodeModel.INT,
									dataReaderJVar.invoke("readUInt32"))));
			break;
		case TIME:
			jBlock.assign(jAssignmentTarget, dataReaderJVar.invoke("readTime"));
			break;
		case DURATION:
			jBlock.assign(jAssignmentTarget,
					dataReaderJVar.invoke("readDuration"));
			break;

		default:

			// Throw exception
			throw new RosMessageGeneratorException("unknown data type: "
					+ dataType);

		}

	}

	private void generateReadStatement(JAssignmentTarget jAssignmentTarget,
			JBlock jBlock) {

		// Add invocation of deserialize method
		jBlock.add(JExpr.invoke(jAssignmentTarget, "deserialize").arg(
				dataReaderJVar));

	}

}
