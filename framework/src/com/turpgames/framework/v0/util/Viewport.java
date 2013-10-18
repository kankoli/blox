package com.turpgames.framework.v0.util;

public class Viewport {
	private float width;
	private float height;
	private float virtualWidth;
	private float virtualHeight;
	private float screenWidth;
	private float screenHeight;
	private float scale;
	private float offsetX;
	private float offsetY;

	private Viewport(float virtualWidth, float virtualHeight) {
		this.virtualWidth = virtualWidth;
		this.virtualHeight = virtualHeight;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getScale() {
		return scale;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public float getVirtualWidth() {
		return virtualWidth;
	}

	public float getVirtualHeight() {
		return virtualHeight;
	}

	public float getScreenWidth() {
		return screenWidth;
	}

	public float getScreenHeight() {
		return screenHeight;
	}

	public void update(float screenWidth, float screenHeight) {
		float wScale = screenWidth / virtualWidth;
		float hScale = screenHeight / virtualHeight;

		this.scale = Math.min(wScale, hScale);

		this.width = scale * virtualWidth;
		this.height = scale * virtualHeight;

		this.offsetX = (screenWidth - width) / 2;
		this.offsetY = (screenHeight - height) / 2;

		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public static Viewport create(float virtualWidth, float virtualHeight, float screenWidth, float screenHeight) {
		Viewport viewport = new Viewport(virtualWidth, virtualHeight);
		viewport.update(screenWidth, screenHeight);
		return viewport;
	}
}
