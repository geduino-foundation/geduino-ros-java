package org.geduino.ros.core.naming.model;

public interface ResolvedName extends Named {
	
	boolean isRoot();
	
	ResolvedName getParent();
	
}
