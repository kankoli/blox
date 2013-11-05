package com.turpgames.framework.v0.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderedMap<K, V> extends HashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<K> orderedKeys;
	
	public OrderedMap() {
		super();
		orderedKeys = new ArrayList<K>();
	}
	
	public List<K> getOrderedKeys() {
		return orderedKeys;
	}
	
	@Override
	public V put (K key, V value) {
		if (!containsKey(key)) orderedKeys.add(key);
		return super.put(key, value);
	}

	@Override
	public void clear () {
		orderedKeys.clear();
		super.clear();
	}
}