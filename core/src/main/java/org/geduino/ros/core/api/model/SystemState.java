package org.geduino.ros.core.api.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SystemState implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Map<String, Set<String>> publishers;
	private final Map<String, Set<String>> subscribers;
	private final Map<String, Set<String>> services;

	public SystemState(Map<String, Set<String>> publishers,
			Map<String, Set<String>> subscribers,
			Map<String, Set<String>> services) {

		this.publishers = publishers;
		this.subscribers = subscribers;
		this.services = services;

	}

	public Map<String, Set<String>> getPublishers() {
		return publishers;
	}

	public Set<String> getPublishers(String topic) {

		// Get publishers
		Set<String> result = publishers.get(topic);

		if (result == null) {

			// Set result to empty set
			result = new HashSet<String>();

		}

		return result;

	}

	public Map<String, Set<String>> getSubscribers() {
		return subscribers;
	}

	public Set<String> getSubscribers(String topic) {

		// Get subscribers
		Set<String> result = subscribers.get(topic);

		if (result == null) {

			// Set result to empty set
			result = new HashSet<String>();

		}

		return result;

	}

	public Map<String, Set<String>> getServices() {
		return services;
	}

	public Set<String> getServices(String service) {

		// Get services
		Set<String> result = services.get(service);

		if (result == null) {

			// Set result to empty set
			result = new HashSet<String>();

		}

		return result;

	}

	@Override
	public String toString() {
		return "SystemState [publishers=" + publishers + ", subscribers="
				+ subscribers + ", services=" + services + "]";
	}

}
