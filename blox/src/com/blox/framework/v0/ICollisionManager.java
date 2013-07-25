package com.blox.framework.v0;

public interface ICollisionManager {
	void register(ICollidable obj);

	void unregister(ICollidable obj);

	void collide();
}
