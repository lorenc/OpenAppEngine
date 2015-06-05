package com.google.appengine.api.urlfetch;

public class HTTPResponse {
	
	private int responseCode;
	private byte[] content;
	private String message;
	
	public HTTPResponse(int code, String message) {
		this.responseCode = code;
		this.message = message;
	}
	
	public int getResponseCode() {
		return this.responseCode;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] value) {
		this.content = value;
	}

}
