package com.google.appengine.api.taskqueue;

import java.util.Timer;

public class QueueFactory {
	
	private static volatile Timer timer = new Timer();
	
	public static Queue getDefaultQueue() {
		Queue q = new Queue();
		q.setTimer(QueueFactory.getTimer());
		return q;
	}
	
	private static Timer getTimer() {
		return QueueFactory.timer;
	}

}
