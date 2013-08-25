package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionGroup;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.util.CollisionDetector;

public class CollisionManager implements ICollisionManager {
	private List<ICollisionGroup> groups;

	public CollisionManager() {
		groups = new ArrayList<ICollisionGroup>();
	}

	@Override
	public void register(ICollisionGroup obj) {
		groups.add(obj);
	}

	@Override
	public void unregister(ICollisionGroup obj) {
		groups.remove(obj);
	}

	@Override
	public void collide() {
		ICollisionGroup group;
		List<ICollidable> first, second;
		for (int i = 0; i < groups.size(); i++) {
			group = groups.get(i);
			if (group.isActive()) {
				first = group.getFirst();
				second = group.getSecond();
				for (int j = 0; j < first.size(); j++) {
					for (int k = 0; k < second.size(); k++) {
						CollisionDetector.detect(group, first.get(j), second.get(k));
					}
				}
			}
		}
	}
}
