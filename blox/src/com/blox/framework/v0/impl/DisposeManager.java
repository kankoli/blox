package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IDisposable;
import com.blox.framework.v0.IDisposeManager;

public class DisposeManager implements IDisposeManager {

	private List<IDisposable> objects;

	public DisposeManager() {
		objects = new ArrayList<IDisposable>();
	}

	@Override
	public void register(IDisposable disposable) {
		if (!objects.contains(disposable))
			objects.add(disposable);
	}

	@Override
	public void dispose(IDisposable disposable) {
		if (objects.remove(disposable))
			disposable.dispose();
	}

	@Override
	public void disposeAll() {
		for (IDisposable disposable : objects)
			disposable.dispose();
		objects.clear();
	}
}
