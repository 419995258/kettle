package org.my431.platform.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Listener that keeps track of the number of sessions that the Web application
 * is currently using and has ever used in its life cycle.
 */

public class SessionCounter implements HttpSessionListener {
	private static int totalSessionCount = 0;

	private static int currentSessionCount = 0;

	private static int maxSessionCount = 0;

	public void sessionCreated(HttpSessionEvent event) {
		totalSessionCount++;
		currentSessionCount++;
		if (currentSessionCount > maxSessionCount) {
			maxSessionCount = currentSessionCount;
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		currentSessionCount--;
	}

	/** The total number of sessions created. */

	public static int getTotalSessionCount() {
		return (totalSessionCount);
	}

	/** The number of sessions currently in memory. */

	public static int getCurrentSessionCount() {
		return (currentSessionCount);
	}

	/**
	 * The largest number of sessions ever in memory at any one time.
	 */

	public static int getMaxSessionCount() {
		return (maxSessionCount);
	}
}
