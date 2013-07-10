package com.blox.framework.v0;

public interface IResourceManager {
	ITexture loadTexture(String resourcePath);
	IMusic loadMusic(String resourcePath);
	ISound loadSound(String resourcePath);
	IVideo loadVideo(String resourcePath);
}
