package org.geduino.ros.node;

import org.apache.log4j.Logger;
import org.geduino.ros.core.exception.RosException;

public class NodeThread extends Thread {

	private static final Logger LOGGER = Logger.getLogger(NodeThread.class);

	private final Node node;

	public NodeThread(Node node) {
		
		super(node.getNodeName().toString());
		
		this.node = node;
		
	}

	@Override
	public void run() {

		super.run();

		try {

			// Run node
			node.run();

		} catch (RosException ex) {

			// Log
			LOGGER.error("an error occurs running node", ex);

		}

	}

}
