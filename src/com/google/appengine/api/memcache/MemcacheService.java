package com.google.appengine.api.memcache;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.spy.memcached.MemcachedClient;

public class MemcacheService {
	private static String LOG_TAG = MemcacheService.class.getName();
	private MemcachedClient client;
	
	public static enum SetPolicy {
		SET_ALWAYS
	}

	public MemcacheService(MemcachedClient client) {
		this.client = client;
	}
	
	public void put(String key, Object item) {
		this.put(key, item, Long.MAX_VALUE);
	}
	
	public void put(String key, Object item, long duration) {
		this.put(key, item, duration, SetPolicy.SET_ALWAYS);
	}
	
	public void put(String key, Object item, long duration, SetPolicy policy) {
		try {
			this.client.set(key, (int) duration, item);
		} catch(Exception e) {
			Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		try {
			return (T) this.client.get(key);
		} catch(Exception e) {
			Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());
			return null;
		}
	}

	public void clearAll() {
		try {
			this.client.flush();
		} catch(Exception e) {
			Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());
		}
	}

	public void delete(String key) {
		try {
			this.client.delete(key);
		} catch(Exception e) {
			Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());
		}
	}

	public boolean contains(String key) {
		try {
			return null != this.get(key);
		} catch(Exception e) {
			Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());
			return false;
		}
	}
}
