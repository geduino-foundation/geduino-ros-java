package org.geduino.ros.core.messages.model;

public class Duration extends RosTime {

	private static final long serialVersionUID = 1L;

	public static final Duration ZERO = new Duration();

	public Duration() {
		super(0, 0);
	}

	public Duration(long seconds, long nanos) {
		super(seconds, nanos);
	}

	public Duration(long millis) {
		super(millis);
	}

	public Duration(Duration duration) {
		super(duration);
	}

	public void sleep() throws InterruptedException {

		// Sleep
		Thread.sleep(getMillis(), (int) getNanosForMillis());

	}

	public Duration add(Duration duration) {

		// Get duration
		Duration sum = new Duration(add(duration));

		return sum;

	}

	public Duration subtract(Duration duration) {

		// Get duration
		Duration sub = new Duration(subtract(duration));

		return sub;

	}

}
