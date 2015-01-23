package org.geduino.ros.core.naming.model;

public interface UnresolvedName extends Named {

	ResolvedName resolve(ResolvedName resolvedName);
	
}
