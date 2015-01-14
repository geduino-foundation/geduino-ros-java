package org.geduino.ros.messages.description.loader;

import java.io.File;
import java.io.FileFilter;

class RosMessageFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {

		// Get accept
		boolean accept = !file.isDirectory() && file.getName().endsWith(".msg");

		return accept;
		
	}

}
