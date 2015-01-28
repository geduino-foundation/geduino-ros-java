package org.geduino.ros.messages.generator;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.messages.description.model.MessageDescription;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JMod;

class DetailsFieldGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(DetailsFieldGenerator.class);

	private final JCodeModel jCodeModel;
	private final MD5Generator md5Generator;

	public DetailsFieldGenerator(JCodeModel jCodeModel,
			MD5Generator md5Generator) {
		this.jCodeModel = jCodeModel;
		this.md5Generator = md5Generator;
	}

	void generate(MessageDescription messageDescription,
			JDefinedClass jDefinedClass) throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding DETAILS field...");

		// Get message name, md5 sum and message definition
		MessageName messageName = messageDescription.getName();
		String md5sum = md5Generator.generate(messageDescription);
		String messageDefinition = messageName.getLastChild().toString()
				.concat(" message");

		// Get message details class
		JClass messageDetailsJClass = jCodeModel.ref(MessageDetails.class)
				.narrow(jDefinedClass);

		// Get message name expression
		JClass nameJClass = jCodeModel.ref(Name.class);
		JExpression messageNameJExpression = nameJClass.staticInvoke(
				"parseMessageName").arg(JExpr.lit(messageName.toString()));

		// Add message details field
		jDefinedClass
				.field(JMod.PUBLIC + JMod.STATIC + JMod.FINAL,
						messageDetailsJClass, "DETAILS")
				.init(JExpr
						._new(messageDetailsJClass)
						.arg(messageNameJExpression)
						.arg(JExpr.lit(md5sum))
						.arg(JExpr.lit(messageDefinition))
						.arg(JExpr
								.direct(jDefinedClass.name().concat(".class"))));
		
	}

}
