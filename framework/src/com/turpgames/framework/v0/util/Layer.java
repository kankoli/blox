package com.turpgames.framework.v0.util;

import java.util.ArrayList;
import java.util.List;

public class Layer<T>{
	private int index;
	private List<T> objects;

	public Layer (int index) {
		this.index = index;
		objects = new ArrayList<T>();
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public List<T> getObjects() {
		return this.objects;
	}
	
	public void register(T obj) {
		objects.add(obj);
	}

	public boolean unregister(T obj) {
		return objects.remove(obj);
	}
}
