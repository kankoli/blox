package com.blox.framework.v0.util;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	private Map<String, Object> cache;

	public Cache() {
		cache = new HashMap<String, Object>();
	}

	public void put(String key, Object value) {
		cache.put(key, value);
	}

	public Object get(String key) {
		return cache.get(key);
	}

	public boolean remove(String key) {
		return cache.remove(key) != null;
	}

	public boolean contains(String key) {
		return cache.containsKey(key);
	}
}
