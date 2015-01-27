package org.geduino.ros.messages.generator;

import java.util.Map;

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

class MessageDetailsFieldGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(MessageDetailsFieldGenerator.class);

	static void generate(JCodeModel jCodeModel, JDefinedClass jDefinedClass,
			MessageDescription messageDescription,
			Map<MessageName, MessageDescription> messageDescriptions)
			throws RosMessageGeneratorException {

		// Log
		LOGGER.trace("adding serialize(DataWriter) method...");

		// Get message name, md5 sum and message definition
		MessageName messageName = messageDescription.getName();
		String md5sum = MD5Generator.generate(messageDescription,
				messageDescriptions);
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
		jDefinedClass.field(JMod.PUBLIC + JMod.STATIC + JMod.FINAL,
				messageDetailsJClass, "DETAILS").init(
				JExpr._new(messageDetailsJClass).arg(messageNameJExpression)
						.arg(JExpr.lit(md5sum))
						.arg(JExpr.lit(messageDefinition))
						.arg(JExpr.direct(jDefinedClass.name().concat(".class"))));

	}

}
