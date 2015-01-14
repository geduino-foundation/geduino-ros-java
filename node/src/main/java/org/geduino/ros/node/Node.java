package org.geduino.ros.node;

import java.net.URI;

import org.geduino.ros.core.exception.RosException;
import org.geduino.ros.core.naming.model.GlobalName;

public abstract class Node {

	private final GlobalName nodeName;
	private final URI nodeUri;

	public Node(GlobalName nodeName, URI nodeUri) {
		this.nodeName = nodeName;
		this.nodeUri = nodeUri;
	}

	public GlobalName getNodeName() {
		return nodeName;
	}

	public URI getNodeUri() {
		return nodeUri;
	}
	
	public abstract void run() throws RosException;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nodeName == null) ? 0 : nodeName.hashCode());
		result = prime * result + ((nodeUri == null) ? 0 : nodeUri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (nodeName == null) {
			if (other.nodeName != null)
				return false;
		} else if (!nodeName.equals(other.nodeName))
			return false;
		if (nodeUri == null) {
			if (other.nodeUri != null)
				return false;
		} else if (!nodeUri.equals(other.nodeUri))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [nodeName=" + nodeName + ", nodeUri=" + nodeUri + "]";
	}

}
