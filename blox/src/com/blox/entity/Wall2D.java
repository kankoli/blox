package com.blox.entity;

import com.badlogic.gdx.math.Vector2;
import com.blox.ScaledShapeRenderer;

public class Wall2D {
	public Vector2 start;
	public Vector2 end;
	public boolean visible;
	
	public Wall2D(float startX, float startY, float endX, float endY) {
		this(new Vector2(startX, startY), new Vector2(endX, endY));
	}
	
	public Wall2D(Vector2 start, Vector2 end) {
		this.start = start;
		this.end = end;
	}
	
	public void render(ScaledShapeRenderer renderer) {
		if (visible)
			renderer.line(start, end);
	}
}
