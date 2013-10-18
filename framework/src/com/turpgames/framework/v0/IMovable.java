package com.turpgames.framework.v0;

import com.turpgames.framework.v0.util.Vector;

public interface IMovable {
	Vector getLocation();

	Vector getVelocity();

	Vector getAcceleration();

	void move();

	void beginMove(IMover mover);
	
	void stopMoving();
}
