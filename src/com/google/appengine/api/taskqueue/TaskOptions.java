package com.google.appengine.api.taskqueue;

public class TaskOptions {

	private long countdownMillis;
	private DeferredTask payload;
	private RetryOptions retryOptions;
	
	public static class Builder {
		public static TaskOptions withCountdownMillis(long value) {
			return new TaskOptions().countdownMillis(value);
		}

		public static TaskOptions withPayload(DeferredTask task) {
			return new TaskOptions().payload(task);
		}
	}

	public TaskOptions countdownMillis(long value) {
		this.countdownMillis = value;
		return this;
	}
	
	public long getCountdownMillis() {
		return this.countdownMillis;
	}

	public TaskOptions payload(DeferredTask value) {
		this.payload = value;
		return this;
	}
	
	public DeferredTask getPayload() {
		return this.payload;
	}

	public TaskOptions retryOptions(RetryOptions value) {
		this.retryOptions = value;
		return this;
	}
	
	public RetryOptions getRetryOptions() {
		return this.retryOptions;
	}
	
}
