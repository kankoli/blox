package com.blox.framework.v0;

public interface IResourceManager {
	ITexture loadTexture(String id);

	ISound loadSound(String id);

	IMusic loadMusic(String id);
}