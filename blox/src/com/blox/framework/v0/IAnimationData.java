package com.blox.framework.v0;

public interface IAnimationData {
	ITexture getFrame(float frameTime);
	void load(ITexture mainTexture, float frameWidth, float frameHeight);
	void dispose();
}
