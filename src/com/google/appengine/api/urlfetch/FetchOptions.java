package com.google.appengine.api.urlfetch;

public class FetchOptions {

	private double deadline;
	
	public static class Builder {
		public static FetchOptions withDeadline(double deadline) {
			return new FetchOptions().withDeadline(deadline);
		}
	}

	public FetchOptions withDeadline(double value) {
		this.deadline = value;
		return this;
	}

	public static FetchOptions withDefaults() {
		return new FetchOptions();
	}
}
