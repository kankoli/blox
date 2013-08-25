package com.blox.framework.v0.util;

public final class AnimationInfo {
	private final String name;
	private final String imagePath;
	private final float frameDuration;
	private final int frameWidth;
	private final int frameHeight;
	private final boolean looping;
	
	public AnimationInfo(String name, String imagePath, float frameDuration, int frameWidth, int frameHeight, boolean looping) {
		this.name = name;
		this.imagePath = imagePath;
		this.frameDuration = frameDuration;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.looping = looping;
	}

	public String getName() {
		return name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public float getFrameDuration() {
		return frameDuration;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public boolean isLooping() {
		return looping;
	}
}
