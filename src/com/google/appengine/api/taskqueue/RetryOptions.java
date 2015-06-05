package com.google.appengine.api.taskqueue;

public class RetryOptions {

	private int retryLimit = 1;
	
	public static final class Builder {

		public static RetryOptions withTaskRetryLimit(int value) {
			return new RetryOptions().retryLimit(value);
		}
		
	}
	
	public RetryOptions retryLimit(int value) {
		this.retryLimit = value;
		return this;
	}
	
	public int getRetryLimit() {
		return this.retryLimit;
	}

}
