package org.geduino.ros.core.api.model;

import java.io.Serializable;
import java.net.URI;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.core.naming.model.GlobalName;

public class SystemState implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Map<GlobalName, Set<URI>> publishers;
	private final Map<GlobalName, Set<URI>> subscribers;
	private final Map<GlobalName, Set<URI>> services;

	public SystemState(Map<GlobalName, Set<URI>> publishers,
			Map<GlobalName, Set<URI>> subscribers,
			Map<GlobalName, Set<URI>> services) {

		this.publishers = publishers;
		this.subscribers = subscribers;
		this.services = services;

	}

	public Map<GlobalName, Set<URI>> getPublishers() {
		return publishers;
	}

	public Set<URI> getPublisher(String topic) {

		// Get publishers
		Set<URI> result = publishers.get(topic);

		if (result == null) {

			// Set result to empty set
			result = new HashSet<URI>();

		}

		return result;

	}

	public Map<GlobalName, Set<URI>> getSubscribers() {
		return subscribers;
	}

	public Set<URI> getSubscriber(String topic) {

		// Get subscribers
		Set<URI> result = subscribers.get(topic);

		if (result == null) {

			// Set result to empty set
			result = new HashSet<URI>();

		}

		return result;

	}

	public Map<GlobalName, Set<URI>> getServices() {
		return services;
	}

	public Set<URI> getService(String service) {

		// Get services
		Set<URI> result = services.get(service);

		if (result == null) {

			// Set result to empty set
			result = new HashSet<URI>();

		}

		return result;

	}

	@Override
	public String toString() {
		return "SystemState [publishers=" + publishers + ", subscribers="
				+ subscribers + ", services=" + services + "]";
	}

}
