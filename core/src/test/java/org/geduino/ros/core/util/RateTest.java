package org.geduino.ros.core.util;

import junit.framework.TestCase;

public class RateTest extends TestCase {

	public void testByPeriod() throws InterruptedException {

		// Get rate
		Rate rate = Rate.byPeriod(500);

		// Sleep
		rate.sleep();

		// Get start millis
		long startMillis = System.currentTimeMillis();

		// Sleep
		rate.sleep();

		// Assert
		assertTrue((System.currentTimeMillis() - startMillis - 500) < 10);

		// Get start millis
		startMillis = System.currentTimeMillis();

		// Sleep
		rate.sleep();

		// Assert
		assertTrue((System.currentTimeMillis() - startMillis - 500) < 10);

		// Get start millis
		startMillis = System.currentTimeMillis();

		// Sleep
		rate.sleep();

		// Assert
		assertTrue((System.currentTimeMillis() - startMillis - 500) < 10);

	}
	
	public void testByFrequency() throws InterruptedException {

		// Get rate
		Rate rate = Rate.byFrequency(2);

		// Sleep
		rate.sleep();

		// Get start millis
		long startMillis = System.currentTimeMillis();

		// Sleep
		rate.sleep();

		// Assert
		assertTrue((System.currentTimeMillis() - startMillis - 500) < 10);

		// Get start millis
		startMillis = System.currentTimeMillis();

		// Sleep
		rate.sleep();

		// Assert
		assertTrue((System.currentTimeMillis() - startMillis - 500) < 10);

		// Get start millis
		startMillis = System.currentTimeMillis();

		// Sleep
		rate.sleep();

		// Assert
		assertTrue((System.currentTimeMillis() - startMillis - 500) < 10);

	}

}
