package com.blox.framework;

import com.badlogic.gdx.math.Vector2;

public class CircleBounds extends Bounds {
	
	protected Vector2 currentParentCenter;
	protected float radius; 
	
	public CircleBounds(BloxSprite parent, float radius) {
		this(parent, radius, new Vector2(0,0));
	}
	
	public CircleBounds(BloxSprite parent, float radius, Vector2 parentOffset) {
		this.parent = parent;
		this.parentOffset = parentOffset;
		this.radius = radius;
	}

	@Override
	protected Vector2 getIntervalX() {
		currentParentCenter = parent.getCenter();
		return new Vector2(currentParentCenter.x - radius + parentOffset.x, 
				currentParentCenter.x + radius + parentOffset.x);
	}

	@Override
	protected Vector2 getIntervalY() {
		currentParentCenter = parent.getCenter();
		return new Vector2(currentParentCenter.y - radius + parentOffset.y, 
				currentParentCenter.y + radius + parentOffset.y);
	}
}
