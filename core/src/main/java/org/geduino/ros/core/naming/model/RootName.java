package org.geduino.ros.core.naming.model;

public class RootName extends Name implements ResolvedName {

	private static final long serialVersionUID = 1L;

	public RootName() {
		super("/");
	}

	@Override
	protected void validate(String string) {
		// NOTHING TO VALIDATE
	}

	@Override
	public boolean isRoot() {
		return true;
	}

	@Override
	public ResolvedName getParent() {
		return null;
	}

}
