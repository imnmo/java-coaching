package com.frotscher.coaching;

/**
 * Utility class for testing. Can be used to wait ("sleep") for 
 * a given number of seconds to simulate doing something useful.
 */
public class Sleeper {

	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException("Interrupted while processing request.", e);
		}
	}
}