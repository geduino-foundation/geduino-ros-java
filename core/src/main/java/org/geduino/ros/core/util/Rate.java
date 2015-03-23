package org.geduino.ros.core.util;

import org.apache.log4j.Logger;

public class Rate {

	private static final Logger LOGGER = Logger.getLogger(Rate.class);

	public static Rate byPeriod(long period) {

		// Create rate
		Rate rate = new Rate(period);

		return rate;

	}

	public static Rate byFrequency(float frequency) {

		// Get period
		long period = (long) (1000 / frequency);

		// Create rate
		Rate rate = new Rate(period);

		return rate;

	}

	private final long period;

	private long lastExecution;

	private Rate(long period) {

		this.period = period;

		lastExecution = 0;

	}

	public void sleep() throws InterruptedException {

		// Get remaining millis
		long remainingMillis = lastExecution + period
				- System.currentTimeMillis();

		if (remainingMillis > 0) {

			// Log
			LOGGER.info("sleeping for: " + remainingMillis + " millis....");

			// Wait remaining time
			Thread.sleep(remainingMillis);

		}

	}

}
