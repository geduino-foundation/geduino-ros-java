package org.geduino.ros.core.messages.model;

import java.io.Serializable;

public class Duration implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long duration;

	public static final Duration ZERO = new Duration();
	
	public Duration() {
		this(0);
	}

	public Duration(Time from, Time to) {
		this(to.getTime() - from.getTime());
	}
	
	public Duration(long duration) {
		this.duration = duration;
	}

	public long getDuration() {
		return duration;
	}

	public void sleep() throws InterruptedException {

		// Sleep
		Thread.sleep(duration);

	}

	public Duration add(Duration duration) {

		// Get duration
		Duration duration2 = new Duration(this.duration + duration.duration);

		return duration2;

	}

	public Duration subtract(Duration duration) {

		// Get duration
		Duration duration2 = new Duration(this.duration - duration.duration);

		return duration2;

	}

	@Override
	public String toString() {
		return "Duration [duration=" + duration + "]";
	}

}
