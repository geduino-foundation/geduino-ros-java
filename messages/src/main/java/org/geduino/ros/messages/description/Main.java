package org.geduino.ros.messages.description;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;
import org.geduino.ros.messages.description.loader.MessageDescriptionsLoader;
import org.geduino.ros.messages.description.model.MessageDescription;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.writer.FileCodeWriter;

public class Main {

	/**
	 * @param args
	 * @throws RosMessageDescriptionException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws JClassAlreadyExistsException
	 * @throws RosMessageGeneratorException
	 */
	public static void main(String[] args)
			throws RosMessageDescriptionException, IOException,
			NoSuchAlgorithmException, JClassAlreadyExistsException,
			RosMessageGeneratorException {

		Map<MessageName, MessageDescription> msgs = MessageDescriptionsLoader
				.load(new File("src/main/resources/org/geduino/ros/messages"));

		Set<MessageName> keys = msgs.keySet();

		//
		// System.out.println(keys);

		JCodeModel jCodeModel = new JCodeModel();
//		MessageClassGenerator msg = new MessageClassGenerator(jCodeModel,
//				"test");
//		for (Iterator<MessageDescription> iterator = msgs.values().iterator(); iterator
//				.hasNext();) {
//
//			MessageDescription md = iterator.next();
//
//			// System.out.println(md.toString() + " - " +
//			// md.getUnmeetDependencies(keys) + " - " + md.getDependencies());
//
//			// System.out.println(md.getName() + " - " + " - " +
//			// MD5Util.computeMD5(md, msgs));
//
//			msg.generate(md);
//
//		}
//
//		//
//		//
//		// // Create message digest
//		// MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");
//		//
//		// // Compute digest
//		// byte[] digest =
//		// md5MessageDigest.digest("0fed2a11c13e11c5571b4e2a995a91a3 layout\nint32[] data".getBytes());
//		//
//		// // Get md5
//		// String md5 = String.format("%032X", new BigInteger(1, digest));
//		//
//		// System.out.println(md5);
//
//		// JCodeModel jCodeModel = new JCodeModel();
//		//
//		// JDefinedClass jDefinedClass =
//		// jCodeModel._class("org.geduino.messages.stdmsgs.StringMsg");
//		// jDefinedClass._extends(Message.class);
//		//
//		// jDefinedClass.javadoc().add("std_msgs/String message - auto generated class");
//		//
//		//
//		//
//		// jDefinedClass.field(JMod.PRIVATE, String[].class, "data");
//		//
//		// JMethod c = jDefinedClass.constructor(1);
//		//
//		// c.body().add(JExpr.invoke("addStringFieldValue").arg("data"));
//		//
//		// JMethod m = jDefinedClass.method(1, String.class, "getData");
//		//
//		// m.body()._return(JExpr.invoke("getStringFieldValue").arg("data").invoke("getStringValue()"));
//		//
//		// JMethod m2 = jDefinedClass.method(1, String.class, "setData");
//		// JVar v = m2.param(String.class, "data");
//		// m2.body().add(JExpr.invoke("getStringFieldValue").arg("data").invoke("setStringValue").arg(v));
//		//
//		//
//		//
//
//		jCodeModel.build(new CodeWriter() {
//
//			private ByteArrayOutputStream stream  = new ByteArrayOutputStream();
//			
//			@Override
//			public OutputStream openBinary(JPackage arg0, String arg1)
//					throws IOException {
//				
//				
//				return stream;
//				
//			}
//
//			@Override
//			public void close() throws IOException {
//				// TODO Auto-generated method stub
//
//				System.out.println(new String(stream.toByteArray()));
//			}
//		});
//		System.out.println(new File("./").getAbsolutePath());
//		jCodeModel.build(new FileCodeWriter(new File("./src/main/java")));
		
		
		
	}

}
