package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IUpdatable;
import com.blox.framework.v0.IUpdateManager;

public class UpdateManager implements IUpdateManager {
	private List<IUpdatable> objects;

	public UpdateManager() {
		objects = new ArrayList<IUpdatable>();
	}

	@Override
	public void register(IUpdatable obj) {
		objects.add(obj);
	}

	@Override
	public void unregister(IUpdatable obj) {
		objects.remove(obj);
	}

	@Override
	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update();
		}
	}
}
