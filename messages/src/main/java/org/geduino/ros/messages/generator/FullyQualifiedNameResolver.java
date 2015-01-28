package org.geduino.ros.messages.generator;

import org.geduino.ros.core.naming.model.MessageName;

class FullyQualifiedNameResolver {

	private final String javaPackage;

	FullyQualifiedNameResolver(String javaPackage) {
		this.javaPackage = javaPackage;
	}

	String resolve(MessageName messageName) {

		// Get fully qualified name
		String fullyQualifiedName = javaPackage.concat(".")
				.concat(messageName.getPackageName().toString()).concat(".")
				.concat(messageName.getLastChild().toString());

		return fullyQualifiedName;

	}

}
