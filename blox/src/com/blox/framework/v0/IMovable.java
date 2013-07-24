package com.blox.framework.v0;

import com.blox.framework.v0.util.Vector;

public interface IMovable {
	Vector getLocation();

	Vector getVelocity();

	void setVelocity(Vector vel);

	void setVelocity(float x, float y);

	Vector getAcceleration();

	void move();

	void setMover(IMover mover);
}
