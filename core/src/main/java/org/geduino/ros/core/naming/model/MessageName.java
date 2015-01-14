package org.geduino.ros.core.naming.model;

import org.geduino.ros.core.naming.exception.RosNamingRuntimeException;

public class MessageName extends Name implements ResolvedName {

	private static final long serialVersionUID = 1L;

	MessageName(String string) {
		super(string);
	}

	@Override
	protected void validate(String string) {

		if (!Name.isMessage(string)) {

			// Throw exception
			throw new RosNamingRuntimeException("invalid message name: "
					+ string);

		}

	}
	
	@Override
	public boolean isRoot() {
		return false;
	}

	@Override
	public ResolvedName getParent() {

		// Get parent name syntax
		String parentNameSyntax = name.substring(0, name.lastIndexOf("/"));

		// Get parent name
		ResolvedName parentName = (ResolvedName) Name
				.parseName(parentNameSyntax);

		return parentName;

	}

	public BaseName getPackageName() {
		
		// Get package name syntax
		String packageNameSyntax = name.substring(0, name.lastIndexOf("/"));

		// Get parent name
		BaseName packageName = (BaseName) Name
				.parseName(packageNameSyntax);

		return packageName;

		
	}
	
}