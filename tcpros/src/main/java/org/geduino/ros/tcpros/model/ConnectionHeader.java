package org.geduino.ros.tcpros.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ConnectionHeader implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Name of node sending data
	 */
	public static String CALLER_ID = "callerid";

	/**
	 * Name of the topic the subscriber is connecting to
	 */
	public static String TOPIC = "topic";

	/**
	 * Name of service the client is calling
	 */
	public static String SERVICE = "service";

	/**
	 * Md5sum of the message type
	 */
	public static String MD5_SUM = "md5sum";

	/**
	 * Message type
	 */
	public static String TYPE = "type";

	/**
	 * Full text of message definition (output of gendeps --cat)
	 */
	public static String MESSAGE_DEFINITION = "message_definition";

	/**
	 * Human-readable error message if the connection is not successful
	 */
	public static String ERROR = "error";

	/**
	 * Sent from a service client to a service. If '1', keep connection open for
	 * multiple requests.
	 */
	public static String PERSISTENT = "persistent";

	/**
	 * Sent from subscriber to publisher. If '1', publisher will set TCP_NODELAY
	 * on socket if possible
	 */
	public static String TCP_NO_DELAY = "tcp_nodelay";

	/**
	 * Publisher is in latching mode (i.e. sends the last value published to new
	 * subscribers)
	 */
	public static String LATCHING = "latching";

	private final Map<String, String> fields;

	public ConnectionHeader() {
		this.fields = new HashMap<String, String>();
	}

	public boolean containsKey(String key) {
		return fields.containsKey(key);
	}

	public Set<Entry<String, String>> fieldSet() {
		return fields.entrySet();
	}

	public String get(String key) {
		return fields.get(key);
	}

	public String put(String key, String value) {
		return fields.put(key, value);
	}

	public int size() {
		return fields.size();
	}

	@Override
	public String toString() {
		return "ConnectionHeader [fields=" + fields + "]";
	}

}
