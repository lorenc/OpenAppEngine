package com.google.appengine.api.taskqueue;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue {
	private static String LOG_TAG = Queue.class.getName();
	private Timer timer;
	
	public void add(final TaskOptions taskOptions) {
		this.timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  try {
					  taskOptions.getPayload().run();
				  } catch(Exception e) {
					  Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());;
				  }
			  }
			}, taskOptions.getCountdownMillis());		
	}

	public void setTimer(Timer value) {
		this.timer = value;
	}

}
