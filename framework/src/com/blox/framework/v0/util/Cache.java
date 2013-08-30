package com.blox.framework.v0.util;

import java.util.HashMap;
import java.util.Map;

public class Cache<T>  {
	private Map<String, T> cache;

	public Cache() {
		cache = new HashMap<String, T>();
	}

	public void put(String key, T value) {
		cache.put(key, value);
	}

	public T get(String key) {
		return cache.get(key);
	}

	public boolean remove(String key) {
		return cache.remove(key) != null;
	}

	public boolean contains(String key) {
		return cache.containsKey(key);
	}
}
