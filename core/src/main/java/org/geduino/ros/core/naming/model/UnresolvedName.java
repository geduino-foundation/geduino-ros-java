package org.geduino.ros.core.naming.model;

public interface UnresolvedName extends Named {

	Name resolve(ResolvedName resolvedName);
	
}
