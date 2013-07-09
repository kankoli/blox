package com.blox.framework;

import com.badlogic.gdx.math.Vector2;

public class CircleBounds extends Bounds {
	
	protected Vector2 position;
	protected float radius; 
	
	public CircleBounds(Vector2 pos, float r) {
		this.position = pos;
		this.radius = r;
	}

	@Override
	protected Vector2 getIntervalX() {
		return new Vector2(position.x-radius, position.x+radius);
	}

	@Override
	protected Vector2 getIntervalY() {
		return new Vector2(position.y-radius, position.y+radius);
	}
}
