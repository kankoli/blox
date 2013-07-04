package com.blox.entity;

import com.badlogic.gdx.math.Vector2;

public class Wall2D {
	public Vector2 start;
	public Vector2 end;
	
	public Wall2D(float startX, float startY, float endX, float endY) {
		this(new Vector2(startX, startY), new Vector2(endX, endY));
	}
	
	public Wall2D(Vector2 start, Vector2 end) {
		this.start = start;
		this.end = end;
	}
}