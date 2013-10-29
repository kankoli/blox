package com.turpgames.framework.v0;

import java.io.InputStream;

public interface IResourceManager {
	void beginLoading();
	
	boolean isLoading();
	
	float getProgress();
	
	ITexture getTexture(String id);

	ISound getSound(String id);

	IMusic getMusic(String id);

	IFont getFont(String id);
	
	InputStream readFile(String path);
}