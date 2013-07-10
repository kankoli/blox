package com.blox.framework;

import com.badlogic.gdx.math.Vector2;

public class CircleBounds extends Bounds {
	
	protected Vector2 currParentCenter;
	protected Vector2 currCenter;
	protected float radius; 
	protected float radiusSquared;
	
	public CircleBounds(BloxSprite parent, float radius) {
		this(parent, radius, new Vector2(0,0));
	}
	
	public CircleBounds(BloxSprite parent, float radius, Vector2 parentOffset) {
		this.parent = parent;
		this.parentOffset = parentOffset;
		this.radius = radius;
		this.radiusSquared = (float) Math.pow(this.radius, 2);
	}
	
	@Override
	public boolean collide(Bounds b) {
		if (b.getClass().equals(RectangleBounds.class)) {
			return collideRect((RectangleBounds)b);
		}
		else if (b.getClass().equals(CircleBounds.class)) {
			return collideCircle((CircleBounds)b);
		}
		return false;
	}
	
	private boolean collideRect(RectangleBounds r) {
		currParentCenter = parent.getCenter();
		currCenter = currParentCenter.add(parentOffset);
		Vector2 rParentPos = r.getCurrentParentPos();
		double d = Math.pow(Math.min(Math.abs(rParentPos.x - currCenter.x), Math.abs(rParentPos.x + r.dimensions.x - currCenter.x)), 2) +
				Math.pow(Math.min(Math.abs(rParentPos.y - currCenter.y), Math.abs(rParentPos.y + r.dimensions.y - currCenter.y)), 2);
		return d < radiusSquared; 
	}
	
	private boolean collideCircle(CircleBounds c) {
		currParentCenter = parent.getCenter();
		Vector2 otherParentCenter = c.parent.getCenter();
		currCenter = currParentCenter.add(parentOffset);
		c.currCenter = otherParentCenter.add(c.parentOffset);
		Vector2 diff = currCenter.sub(c.currCenter);
		double d = Math.sqrt(Math.pow(diff.x,2) + Math.pow(diff.y, 2));
		return d < radius + c.radius;
	}
	
	@Override
	protected Vector2 getIntervalX() {
		currParentCenter = parent.getCenter();
		return new Vector2(currParentCenter.x - radius + parentOffset.x, 
				currParentCenter.x + radius + parentOffset.x);
	}

	@Override
	protected Vector2 getIntervalY() {
		currParentCenter = parent.getCenter();
		return new Vector2(currParentCenter.y - radius + parentOffset.y, 
				currParentCenter.y + radius + parentOffset.y);
	}
}
