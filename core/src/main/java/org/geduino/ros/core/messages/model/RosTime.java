package org.geduino.ros.core.messages.model;

import java.io.Serializable;

public class RosTime implements Serializable {

	private static final long serialVersionUID = 1L;

	protected final long seconds;
	protected final long nanos;

	public static final long NONOS_IN_SECOND = 1000000000;
	public static final long NONOS_IN_MILLI = 1000000;
	public static final long MILLIS_IN_SECOND = 1000;

	protected RosTime(long millis) {
		this(millis / MILLIS_IN_SECOND, (millis % MILLIS_IN_SECOND)
				* NONOS_IN_MILLI);
	}

	protected RosTime(RosTime rosTime) {
		this(rosTime.seconds, rosTime.nanos);
	}

	protected RosTime(long seconds, long nanos) {
		this.seconds = seconds + nanos / NONOS_IN_SECOND;
		this.nanos = nanos % NONOS_IN_SECOND;
	}

	public long getSeconds() {
		return seconds;
	}

	public long getNanos() {
		return nanos;
	}

	public long getMillis() {

		// Get millis
		long millis = (seconds * MILLIS_IN_SECOND) + nanos / NONOS_IN_MILLI;

		return millis;

	}

	public long getNanosForMillis() {

		// Get nanos for millis
		long nanosForMillis = nanos % NONOS_IN_MILLI;

		return nanosForMillis;

	}

	protected RosTime add(RosTime rosTime) {

		// Get added ros time
		RosTime add = new RosTime(seconds + rosTime.seconds, nanos
				+ rosTime.nanos);

		return add;

	}

	protected RosTime subtract(RosTime rosTime) {

		// Get subtracted ros time
		RosTime add = new RosTime(seconds - rosTime.seconds, nanos
				- rosTime.nanos);

		return add;

	}

	protected boolean greatherThan(RosTime rosTime) {

		// Get greather than
		boolean greatherThen = (seconds > rosTime.seconds)
				|| (seconds == rosTime.seconds && nanos > rosTime.nanos);

		return greatherThen;

	}

	protected boolean lessThan(RosTime rosTime) {

		// Get less than
		boolean lessThen = (seconds < rosTime.seconds)
				|| (seconds == rosTime.seconds && nanos < rosTime.nanos);

		return lessThen;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (nanos ^ (nanos >>> 32));
		result = prime * result + (int) (seconds ^ (seconds >>> 32));
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
		RosTime other = (RosTime) obj;
		if (nanos != other.nanos)
			return false;
		if (seconds != other.seconds)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RosTime [seconds=" + seconds + ", nanos=" + nanos + "]";
	}

}
