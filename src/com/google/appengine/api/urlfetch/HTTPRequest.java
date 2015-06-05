package com.google.appengine.api.urlfetch;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTTPRequest {

	private List<HTTPHeader> headers;
	private byte[] payload;
	private HTTPMethod method;
	private URL url;
	private FetchOptions options;
	
	public HTTPRequest(URL url, HTTPMethod method, FetchOptions options) {
		this.url = url;
		this.method = method;
		this.options = options;
	}

	public HTTPRequest(URL url, HTTPMethod method) {
		this(url, method, FetchOptions.withDefaults());
	}

	public HTTPMethod getMethod() {
		return this.method;
	}
	
	public URL getURL() {
		return this.url;
	}
	
	public byte[] getPayload() {
		return this.payload;
	}
	
	public void setHeader(HTTPHeader httpHeader) {
		if (null == this.headers) {
			this.headers = new ArrayList<HTTPHeader>();
		}
		this.headers.add(httpHeader);
	}

	public List<HTTPHeader> getHeaders() {
		return this.headers;
	}
	
	public void setPayload(byte[] bytes) {
		this.payload = bytes;
	}	
}
