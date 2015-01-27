package org.geduino.ros.messages.description.loader;

import java.util.ArrayList;
import java.util.List;

class RawMessage {

	private final List<String> lines;

	private String staged;

	RawMessage() {
		this.lines = new ArrayList<String>();
	}

	void stage(String string) {

		if (staged == null) {

			// Set string as staged
			staged = string;

		} else {

			// Concat string to staged
			staged = staged.concat(string);

		}

	} 

	void commit() {

		if (staged != null) {

			// Add staged to lines
			lines.add(staged);

			// Set staged to null
			staged = null;

		}

	}

	List<String> getLines() {
		return lines;
	}

}
