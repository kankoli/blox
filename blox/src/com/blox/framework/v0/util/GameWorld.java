package com.blox.framework.v0.util;

public class GameWorld {
	private Viewport viewport;
	
	public final Vector gravity;

	GameWorld() {
		gravity = new Vector();
	}
	
	public void setViewport(float width, float height, float screenWidth, float screenHeight) {
		viewport = Viewport.create(width, height, screenWidth, screenHeight);
	}

	public float getScale() {
		return viewport.getScale();
	}
	
	public Viewport getViewport() {
		return viewport;
	}
}
