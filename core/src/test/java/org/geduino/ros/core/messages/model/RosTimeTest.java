package org.geduino.ros.core.messages.model;

import junit.framework.TestCase;

public class RosTimeTest extends TestCase {

	public void testConstructors() {

		// Asserts
		assertEquals(2, 500000000, new RosTime(1, 1500000000));
		assertEquals(2, 500000000, new RosTime(2500));
		assertEquals(2, 500000000, new RosTime(new Time(1, 1500000000)));

	}

	public void testConversion() {

		// Create ros time
		RosTime rosTime = new RosTime(1, 1500000500);

		// Asserts
		assertEquals(2500, rosTime.getMillis());
		assertEquals(500, rosTime.getNanosForMillis());

	}

	public void testOperators() {

		// Create ros times
		RosTime rosTime1 = new RosTime(2, 1500001000);
		RosTime rosTime2 = new RosTime(1, 500000500);

		// Asserts
		assertEquals(5, 1500, rosTime1.add(rosTime2));
		assertEquals(2, 500, rosTime1.subtract(rosTime2));

	}

	public void testComparators() {

		// Create ros times
		RosTime rosTime1 = new RosTime(0, RosTime.MILLIS_IN_SECOND - 1);
		RosTime rosTime2 = new RosTime(1, 0);
		RosTime rosTime3 = new RosTime(1, 1);

		// Asserts
		assertTrue(rosTime1.lessThan(rosTime2));
		assertFalse(rosTime1.greatherThan(rosTime2));
		assertFalse(rosTime1.equals(rosTime2));
		assertTrue(rosTime2.lessThan(rosTime3));
		assertFalse(rosTime2.greatherThan(rosTime3));
		assertFalse(rosTime2.equals(rosTime3));

	}

	void assertEquals(long expectedSeconds, long expectedNanos, RosTime rosTime) {

		// Asserts
		assertEquals("seconds", expectedSeconds, rosTime.getSeconds());
		assertEquals("nanos", expectedNanos, rosTime.getNanos());

	}

}
