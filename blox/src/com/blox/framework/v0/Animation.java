package com.blox.framework.v0;

public class Animation implements IAnimation {
	protected float frameTime;

	private boolean isAnimating;
	private boolean isLooping;
	private Rotation rotation;

	private IAnimationData data;

	public Animation() {
		rotation = new Rotation();
	}

	public void load(ITexture mainTexture, float frameWidth, float frameHeight) {
		data = GameWorld.getAnimationDataFactory().create();
		data.load(mainTexture, frameWidth, frameHeight);
	}

	public boolean isAnimating() {
		return isAnimating;
	}

	public boolean isLooping() {
		return isLooping;
	}

	public void setLooping(boolean looping) {
		isLooping = looping;
	}

	public Rotation getRotation() {
		return rotation;
	}

	public void start() {
		if (isAnimating)
			return;
		frameTime = 0;
		isAnimating = true;
	}

	public void stop() {
		if (!isAnimating)
			return;
		frameTime = 0;
		isAnimating = false;
	}

	public ITexture getFrame() {
		frameTime += GameWorld.getDeltaTime();
		return data.getFrame(frameTime);
	}
}
