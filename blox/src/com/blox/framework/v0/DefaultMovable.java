package com.blox.framework.v0;

public class DefaultMovable implements IMovable {

	private Vector location;
	private Vector velocity;
	private Vector acceleration;
	private IMover mover;

	public DefaultMovable(Vector location, Vector velocity, Vector acceleration) {
		this(location, velocity, acceleration, new DefaultMover());
	}

	public DefaultMovable(Vector location, Vector velocity, Vector acceleration, IMover mover) {
		this.location = location;
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.mover = mover;
	}

	@Override
	public Vector getLocation() {
		return location;
	}

	@Override
	public Vector getVelocity() {
		return velocity;
	}

	@Override
	public Vector getAcceleration() {
		return acceleration;
	}

	@Override
	public void move() {
		mover.move(this);
	}
}
