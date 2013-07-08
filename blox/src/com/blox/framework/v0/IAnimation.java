package com.blox.framework.v0;

public interface IAnimation {

	boolean isAnimating();
	boolean isLooping();
	void setLooping(boolean looping);
	
	void registerEndListener(IAnimationEndListener handler);
	void unregisterEndListener(IAnimationEndListener handler);
	
	void start();
	void stop();
	ITexture getFrame();
	void load(ITexture main, float frameWidth, float frameHeight);
}
