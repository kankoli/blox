package com.blox.framework.v0;

import java.util.List;

public abstract class AnimationData {
	private ITexture mainTexture;
	protected float frameWidth;
	protected float frameHeight;
	protected float frameCount;

	protected List<ITexture> textures;
	protected float animationDuration;

	protected AnimationData(ITexture mainTexture, float frameWidth, float frameHeight) {
		this.mainTexture = mainTexture;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		loadFrames();
	}

	protected abstract void loadFrames();

	public abstract ITexture getFrame(float frameTime);

	public void dispose() {
		for (ITexture texture : textures)
			texture.dispose();
		mainTexture.dispose();
	}
}
