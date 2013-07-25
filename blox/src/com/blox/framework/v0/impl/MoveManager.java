package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.IMoveManager;

public class MoveManager implements IMoveManager {
	private List<IMovable> objects;

	public MoveManager() {
		objects = new ArrayList<IMovable>();
	}

	@Override
	public void register(IMovable obj) {
		objects.add(obj);
	}

	@Override
	public void unregister(IMovable obj) {
		objects.remove(obj);
	}

	@Override
	public void move() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).move();
		}
	}

}
