package com.blox.framework.v0.util;

import com.blox.framework.v0.ITexture;

public class Animation {
	private float frameTime;
	private float frameDuration; // seconds
	private int width;
	private int height;

	private boolean isAnimating;
	private boolean isLooping;
	private String name;

	private Animator manager;

	private ITexture[] textures;

	Animation() {

	}

	void setWidth(int width) {
		this.width = width;
	}

	void setHeight(int height) {
		this.height = height;
	}

	void setManager(Animator manager) {
		this.manager = manager;
	}

	void setName(String name) {
		this.name = name;
	}

	void setFrames(ITexture... textures) {
		this.textures = textures;
	}

	public boolean isStatic() {
		return textures.length == 1;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setFrameDuration(float frameDuration) {
		this.frameDuration = frameDuration;
	}

	public void setLooping(boolean looping) {
		isLooping = looping;
	}

	public String getName() {
		return name;
	}

	public void reset() {
		frameTime = 0;
	}

	public boolean isAnimating() {
		return isAnimating;
	}

	public boolean isLooping() {
		return isLooping;
	}

	public void start(boolean forceRestart) {
		if (forceRestart)
			reset();
		if (isAnimating)
			return;
		isAnimating = true;
	}

	public void pause() {
		isAnimating = false;
	}

	public void stop() {
		if (!isAnimating)
			return;
		frameTime = 0;
		isAnimating = false;
	}

	public ITexture getFrame() {
		if (isStatic())
			return textures[0];

		if (isAnimating)
			frameTime += Game.getDeltaTime();

		int frameIndex = (int) (frameTime / frameDuration);
		if (frameIndex >= textures.length) {
			if (isLooping) {
				frameIndex = frameIndex % textures.length;
			} else {
				frameIndex = textures.length - 1;
				manager.notifyEndListeners(this);
			}
		}
		return textures[frameIndex];
	}
}
