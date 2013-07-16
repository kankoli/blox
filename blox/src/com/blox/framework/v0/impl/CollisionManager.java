package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionManager;

public class CollisionManager implements ICollisionManager {
	private List<ICollidable> objects; 
	
	public CollisionManager() {
		objects = new ArrayList<ICollidable>();
	}
	
	@Override
	public void register(ICollidable obj) {
		objects.add(obj);
	}

	@Override
	public void unregister(ICollidable obj) {
		objects.remove(obj);
	}

	@Override
	public void collide() {
		for(int i = 0; i < objects.size(); i++) {
			ICollidable obj1 = objects.get(i);
			for (int j = i+1; j < objects.size(); j++) {
				CollisionDetector.detect(obj1, objects.get(j));
			}
		}
	}
}
