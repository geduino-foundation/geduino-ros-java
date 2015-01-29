package org.geduino.ros.core.messages.model;

public class Time extends RosTime {

	private static final long serialVersionUID = 1L;

	public static Time now() {

		// Get now
		Time now = new Time();

		return now;

	}

	public Time() {
		super(System.currentTimeMillis());
	}

	public Time(long seconds, long nanos) {
		super(seconds, nanos);
	}

	public Time(long millis) {
		super(millis);
	}

	public Time(Time time) {
		super(time);
	}

	public Time add(Duration duration) {

		// Get time
		Time add = new Time(add(duration));

		return add;

	}

	public Time subtract(Duration duration) {

		// Get time
		Time add = new Time(subtract(duration));

		return add;

	}

}
