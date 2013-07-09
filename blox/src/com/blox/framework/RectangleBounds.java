package com.blox.framework;

import com.badlogic.gdx.math.Vector2;

public class RectangleBounds extends Bounds {
	
	protected Vector2 dimensions;
	protected Vector2 currentParentPos;
	
	public RectangleBounds(BloxSprite parent, float width, float height) {
		this(parent, new Vector2(width, height), new Vector2(0,0));
	}
	
	public RectangleBounds(BloxSprite parent, Vector2 dimensions) {
		this(parent, dimensions, new Vector2(0,0));
	}
	
	public RectangleBounds(BloxSprite parent, Vector2 dimensions, Vector2 parentOffset) {
		this.parent = parent;
		this.dimensions = dimensions;
		this.parentOffset = parentOffset;
	}
	
	protected Vector2 getIntervalX() {
		currentParentPos = parent.getPosition();
		return new Vector2(currentParentPos.x + parentOffset.x, 
				currentParentPos.x + parentOffset.x + dimensions.x);
	}
	
	protected Vector2 getIntervalY() {
		currentParentPos = parent.getPosition();
		return new Vector2(currentParentPos.y + parentOffset.y, 
				currentParentPos.y + parentOffset.y + dimensions.y);
	}
}
