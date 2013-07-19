package com.blox.framework.v0;

public interface IResourceManager {
	ITexture loadTexture(String resourcePath);
	ISound loadSound(String resourcePath);
	IMusic loadMusic(String resourcePath);
	IVideo loadVideo(String resourcePath);
	IFont loadFont(String resourcePath);
}
