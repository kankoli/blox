package com.blox.framework.v0.util;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.IRectangleBound;

public class RectangleBound extends Bound implements IRectangleBound {
	private float width;
	private float height;

	public RectangleBound(Vector location, Rotation rotation, float width, float height) {
		super(location, rotation);
		this.width = width;
		this.height = height;
	}

	public RectangleBound(float x, float y, float z, float rx, float ry, float rz,
			float ox, float oy, float oz, float width, float height) {
		super(x, y, z, rx, ry, rz, ox, oy, oz);
		this.width = width;
		this.height = height;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public int getType() {
		return IBound.Rectangle;
	}
}