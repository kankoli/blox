package com.blox.framework.v0;

public interface IResourceManager {
	void beginLoading();
	
	boolean isLoading();
	
	int getLoadingPercent();
	
	ITexture getTexture(String id);

	ISound getSound(String id);

	IMusic getMusic(String id);

	IFont getFont(String id);
}