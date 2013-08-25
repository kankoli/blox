package com.blox.framework.v0;

import java.io.InputStream;

public interface IResourceManager {
	ITexture loadTexture(String resourcePath);

	ISound loadSound(String resourcePath);

	IMusic loadMusic(String resourcePath);

	IVideo loadVideo(String resourcePath);
	
	InputStream readFile(String resourcePath);
}
