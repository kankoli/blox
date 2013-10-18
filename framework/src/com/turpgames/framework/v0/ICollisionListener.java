package com.turpgames.framework.v0;

import com.turpgames.framework.v0.util.CollisionEvent;

public interface ICollisionListener {
	void onCollide(CollisionEvent event);

	void onNotCollide(CollisionEvent event);
}
