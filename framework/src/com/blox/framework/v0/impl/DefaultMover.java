package com.blox.framework.v0.impl;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.IMover;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class DefaultMover implements IMover {
	@Override
	public void move(IMovable movable) {
		float dt = Game.getDeltaTime();
		float dt2 = dt * dt;

		Vector l = movable.getLocation();
		Vector v = movable.getVelocity();
		Vector a = movable.getAcceleration();

		l.x += v.x * dt + 0.5f * a.x * dt2;
		l.y += v.y * dt + 0.5f * a.y * dt2;
		l.z += v.z * dt + 0.5f * a.z * dt2;

		v.x += a.x * dt;
		v.y += a.y * dt;
		v.z += a.z * dt;
	}
}
