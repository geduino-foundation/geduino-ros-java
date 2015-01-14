package org.geduino.ros.core.naming.model;

import org.geduino.ros.core.naming.exception.RosNamingRuntimeException;

public class GlobalName extends Name implements ResolvedName {

	private static final long serialVersionUID = 1L;

	public GlobalName(String globalName) {
		super(globalName);
	}
	
	@Override
	protected void validate(String string) {
		
		if (!Name.isGlobal(string)) {
			
			// Throw exception
			throw new RosNamingRuntimeException("invalid global name: " + string);
			
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
		ResolvedName parentName = (ResolvedName) Name.parseName(parentNameSyntax);

		return parentName;

	}

}
