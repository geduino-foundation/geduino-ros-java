package org.geduino.ros.core.util;

import java.util.Collection;
import java.util.Iterator;

public class CollectionFilter<T> {

	private final Collection<T> collection;

	public CollectionFilter(Collection<T> collection) {
		this.collection = collection;
	}

	public void filter(Collection<T> targetCollection, Filter<T> filter) {

		for (Iterator<T> iterator = collection.iterator(); iterator.hasNext();) {

			// Get next object
			T object = iterator.next();

			// Apply filter
			boolean accept = filter.accept(object);

			if (accept) {

				// Add accepted object to target collection
				targetCollection.add(object);

			}

		}

	}

}
