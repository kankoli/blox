package com.blox.framework.v0;

public interface IResourceManager {
	void init(IResourceInitListener listener);
	
	ITexture getTexture(String id);

	ISound getSound(String id);

	IMusic getMusic(String id);

	IFont getFont(String id, int size);
}