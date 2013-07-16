package com.blox.framework.v0.impl;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.IRectangleBound;
import com.blox.framework.v0.util.Vector;

public class RectangleBound extends Bound implements IRectangleBound {
	private float width;
	private float height;

	public RectangleBound(ICollidable parent, Vector offset, float width, float height) {
		super(parent, offset);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Vector getLocation() {
		Vector pLocation = parent.getLocation();
		float pWidth = parent.getWidth();
		float pHeight = parent.getHeight();
		
		location.x = parent.isFlipX() ? pLocation.x + pWidth - (offset.x + width) : pLocation.x + offset.x;
		location.y = parent.isFlipY() ? pLocation.y + pHeight - (offset.y + height) : pLocation.y + offset.y;
		return location;
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