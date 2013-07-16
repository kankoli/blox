package com.blox.framework.v0.impl;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICircleBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.util.Vector;

public class CircleBound extends Bound implements ICircleBound {
	private float radius;

	public CircleBound(ICollidable parent, Vector offset, float radius) {
		super(parent, offset);
		this.radius = radius;
	}

	@Override
	public Vector getLocation() {
		Vector pLocation = parent.getLocation();
		float pWidth = parent.getWidth();
		float pHeight = parent.getHeight();
		
		location.x = parent.isFlipX() ? pLocation.x + pWidth - offset.x : pLocation.x + offset.x;
		location.y = parent.isFlipY() ? pLocation.y + pHeight - offset.y : pLocation.y + offset.y;
		return location;
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
