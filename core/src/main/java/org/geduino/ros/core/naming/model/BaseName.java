package org.geduino.ros.core.naming.model;

import org.geduino.ros.core.naming.exception.RosNamingRuntimeException;

public class BaseName extends Name implements UnresolvedName {

	private static final long serialVersionUID = 1L;

	public BaseName(String baseName) {
		super(baseName);
	}
	
	@Override
	protected void validate(String string) {

		if (!Name.isBase(string)) {

			// Throw exception
			throw new RosNamingRuntimeException("invalid base name: " + string);

		}

	}

	@Override
	public Name resolve(ResolvedName resolvedName) {

		if (resolvedName.isRoot()) {

			// Get resolved name syntax
			String nameSyntax = "/".concat(this.name);

			// Create resolved name
			Name name = Name.parseName(nameSyntax);

			return name;

		} else {

			// Get parent
			ResolvedName parent = resolvedName.getParent();

			// Get resolved name syntax
			String nameSyntax = parent.getName().name.concat(
					parent.isRoot() ? "" : "/").concat(this.name);

			// Create resolved name
			Name name = Name.parseName(nameSyntax);

			return name;

		}

	}

}
