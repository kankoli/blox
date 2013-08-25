package com.blox.framework.v0;

public interface ICollisionManager {
	void register(ICollisionGroup obj);

	void unregister(ICollisionGroup obj);

	void collide();
}
