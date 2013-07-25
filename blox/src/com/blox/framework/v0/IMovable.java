package com.blox.framework.v0;

import com.blox.framework.v0.util.Vector;

public interface IMovable {
	Vector getLocation();

	Vector getVelocity();

	Vector getAcceleration();

	void move();

	void setMover(IMover mover);
}
