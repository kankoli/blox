package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

public abstract class Manager<T> {
	protected final List<T> items;

	protected Manager() {
		items = new ArrayList<T>();
	}

	public void register(T item) {
		if (!items.contains(item))
			items.add(item);
	}

	public void unregister(T item) {
		items.remove(item);
	}
	
	public void empty() {
		items.clear();
	}
	
	public void execute() {
		for (int i = items.size() - 1; i >= 0 && i < items.size(); i--)
			execute(items.get(i));
	}
	
	protected abstract void execute(T item);
}
