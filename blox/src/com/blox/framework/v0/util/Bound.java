package com.blox.framework.v0.util;

import com.blox.framework.v0.IBound;

public abstract class Bound implements IBound {
	private Vector location;
	private Rotation rotation;

	protected Bound(Vector location, Rotation rotation) {
		this.location = new Vector(location);
		this.rotation = new Rotation(rotation);
	}

	protected Bound(float x, float y, float z, float rx, float ry, float rz,
			float ox, float oy, float oz) {
		this.location = new Vector(x, y, z);
		this.rotation = new Rotation(rx, ry, rz, ox, oy, oz);
	}

	@Override
	public Vector getLocation() {
		return location;
	}

	@Override
	public Rotation getRotation() {
		return rotation;
	}
}