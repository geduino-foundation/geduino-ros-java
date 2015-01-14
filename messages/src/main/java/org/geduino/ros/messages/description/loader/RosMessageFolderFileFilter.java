package org.geduino.ros.messages.description.loader;

import java.io.File;
import java.io.FileFilter;

import org.geduino.ros.core.naming.model.Name;

class RosMessageFolderFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {

		// Get accept
		boolean accept = Name.isBase(file.getName()) && file.isDirectory();

		return accept;

	}

}
