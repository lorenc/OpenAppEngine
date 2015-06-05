package com.google.appengine.api.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.spy.memcached.MemcachedClient;

public class MemcacheServiceFactory {
	private static String LOG_TAG = MemcacheServiceFactory.class.getName();
	private static volatile MemcachedClient client;
	private static Object clientLock = new Object();
	private static String host;
	private static int port;
	
	public static void setHost(String value, int port) {
		MemcacheServiceFactory.host = value;
		MemcacheServiceFactory.port = port;
	}
	
	public static MemcacheService getMemcacheService() {
		return new MemcacheService(MemcacheServiceFactory.getClient());
	}		

	private static MemcachedClient getClient() {
		if (null != MemcacheServiceFactory.client) {
			return MemcacheServiceFactory.client;
		}
		
		synchronized(MemcacheServiceFactory.clientLock) {
			if (null != MemcacheServiceFactory.client) {
				return MemcacheServiceFactory.client;
			}
			
			if (null == MemcacheServiceFactory.host) {
				throw new UnsupportedOperationException("Must call setHost on MemcacheServiceFactory before using");
			}
			
			try {
				MemcacheServiceFactory.client = new MemcachedClient(new InetSocketAddress(MemcacheServiceFactory.host, MemcacheServiceFactory.port));
			} catch(IOException e) {
				Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());
			}
		}
		return MemcacheServiceFactory.client;
	}
}
