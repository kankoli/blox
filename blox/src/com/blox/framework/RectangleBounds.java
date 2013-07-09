package com.blox.framework;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectangleBounds extends Bounds {

	protected Rectangle bound;
	
	public RectangleBounds(Rectangle r) {
		this.bound = r;
	}
	
	public RectangleBounds(float x, float y, float width, float height) {
		this.bound = new Rectangle(x, y, width, height);
	}
	
	protected Vector2 getIntervalX() {
		return new Vector2(bound.x, bound.x+bound.width);
	}
	
	protected Vector2 getIntervalY() {
		return new Vector2(bound.y, bound.y+bound.height);
	}
}
