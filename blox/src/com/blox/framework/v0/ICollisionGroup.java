package com.blox.framework.v0;

import java.util.List;

import com.blox.framework.v0.util.CollisionEvent;

public interface ICollisionGroup {
	void registerFirst(ICollidable obj);

	void registerSecond(ICollidable obj);

	void unregisterFirst(ICollidable obj);

	void unregisterSecond(ICollidable obj);

	List<ICollidable> getFirst();

	List<ICollidable> getSecond();

	boolean isActive();

	void activate();

	void deactivate();

	boolean onCollide(CollisionEvent event);

	boolean onNotCollide(CollisionEvent event);
	
	void registerCollisionListener(ICollisionListener listener);

	void unregisterCollisionListener(ICollisionListener listener);

	void registerNotCollisionListener(ICollisionListener listener);

	void unregisterNotCollisionListener(ICollisionListener listener);
}
