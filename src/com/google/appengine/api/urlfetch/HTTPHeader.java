package com.google.appengine.api.urlfetch;

public class HTTPHeader {

	private String name;
	private String value;
	
	public HTTPHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValue() {
		return this.value;
	}
}
