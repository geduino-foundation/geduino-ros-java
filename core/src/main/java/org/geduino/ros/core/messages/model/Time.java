package org.geduino.ros.core.messages.model;

import java.io.Serializable;
import java.util.Date;

public class Time implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long time;

	public static final Time ZERO = new Time(0);
	
	public Time() {
		this(System.currentTimeMillis());
	}

	public Time(long time) {
		this.time = time;
	}
	
	public Time(Time time) {
		this.time = time.time;
	}
	
	public Time(Date date) {
		this.time = date.getTime();
	}

	public long getTime() {
		return time;
	}

	public Date toDate() {
		
		// Get date
		Date date = new Date(time);
		
		return date;
		
	}
	
	public Time add(Duration duration) {

		// Get time
		Time time = new Time(this.time + duration.getDuration());

		return time;

	}

	public Time subtract(Duration duration) {

		// Get time
		Time time = new Time(this.time - duration.getDuration());

		return time;

	}

	public Duration ellapsedFrom(Time from) {

		// Get duration
		Duration duration = new Duration(time - from.time);

		return duration;

	}

	public Duration ellapsedTo(Time to) {

		// Get duration
		Duration duration = new Duration(to.time - time);

		return duration;

	}

	@Override
	public String toString() {
		return "Time [time=" + time + "]";
	}

}
