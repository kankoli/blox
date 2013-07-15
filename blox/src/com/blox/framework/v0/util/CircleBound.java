package com.blox.framework.v0.util;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICircleBound;

public class CircleBound extends Bound implements ICircleBound {
	private float radius;

	public CircleBound(Vector location, Rotation rotation, float radius) {
		super(location, rotation);
		this.radius = radius;
	}

	public CircleBound(float x, float y, float z, float rx, float ry,
			float rz, float ox, float oy, float oz, float radius) {
		super(x, y, z, rx, ry, rz, ox, oy, oz);
		this.radius = radius;
	}

	@Override
	public float getRadius() {
		return radius;
	}

	@Override
	public int getType() {
		return IBound.Circle;
	}
}
