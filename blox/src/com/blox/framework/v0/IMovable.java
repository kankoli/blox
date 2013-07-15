package com.blox.framework.v0;

public interface IMovable {
	Vector getLocation();
	Vector getVelocity();
	Vector getAcceleration();
	void move();
}
