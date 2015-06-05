package com.google.appengine.api.urlfetch;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLFetchService {
	private static String LOG_TAG = URLFetchService.class.getName();
	private HttpURLConnection connection;

	public HTTPResponse fetch(HTTPRequest request) {
		HTTPResponse response = null;
		try {
			this.connection = (HttpURLConnection)request.getURL().openConnection();
			connection.setRequestMethod(request.getMethod().name());
			connection.setUseCaches (false);
			connection.setDoOutput(true);
			
			if (null != request.getHeaders()) {
				for (HTTPHeader header : request.getHeaders()) {
					connection.setRequestProperty(header.getName(), header.getValue());
				}
			}

			if (null != request.getPayload()) {
				connection.setDoInput(true);
				DataOutputStream wr = new DataOutputStream (this.connection.getOutputStream());
				wr.write(request.getPayload());
				wr.flush();
				wr.close();	    	  
			}	      

			response = new HTTPResponse(this.connection.getResponseCode(), this.connection.getResponseMessage());

			// get the response
			InputStream is = this.connection.getInputStream();	      
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while (is.available() > 0) {
				bos.write(is.read());
			}

			bos.flush();
			response.setContent(bos.toByteArray());
			is.close();
		} catch(Exception e) {
			Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());
			if (null == response) {
				response = new HTTPResponse(500, "Internal Server Error");
			}
		} finally {
			if (null != this.connection) {
				this.connection.disconnect();
				this.connection = null;
			}
		}
		return response;
	}

}
