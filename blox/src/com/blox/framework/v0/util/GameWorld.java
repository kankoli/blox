package com.blox.framework.v0.util;

public class GameWorld {
	private float screenWidth;
	private float screenHeight;
	private Viewport viewport;
	
	public final Vector gravity;

	GameWorld() {
		gravity = new Vector();
	}
	
	public void setViewport(float width, float height, float screenWidth, float screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		viewport = Viewport.create(width, height, screenWidth, screenHeight);
	}

	public float getScreenHeight() {
		return screenHeight;
	}

	public float getScreenWidth() {
		return screenWidth;
	}
	
	public float getScale() {
		return viewport.getScale();
	}
	
	public Vector getOffset() {
		return viewport.getOffset();
	}
}
