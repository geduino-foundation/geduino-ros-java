package org.geduino.ros.core.util;

public interface Filter<T> {

	public boolean accept(T object);

}
