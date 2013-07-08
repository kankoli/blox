package com.blox.framework.v0;

public class Animation {
	private AnimationData data;
	
	private boolean isAnimating;
	
	protected float frameTime;
	
	public void load() {
		
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
	
	public void reset() {
		frameTime = 0;
	}
	
	public ITexture getFrame() {
		frameTime += GameWorld.getDeltaTime();
		return data.getFrame(frameTime);
	}
}
