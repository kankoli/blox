package com.blox.framework.v0.impl;

import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.IVideo;
import com.blox.framework.v0.util.Cache;

public class CachedResourceManager implements IResourceManager {

	private Cache cache;
	private IResourceManager resManager;

	public CachedResourceManager(IResourceManager resManager) {
		this.resManager = resManager;
		cache = new Cache();
	}

	@Override
	public ITexture loadTexture(String resourcePath) {
		Object cacheVal = cache.get(resourcePath);
		if (cacheVal != null)
			return (ITexture) cacheVal;

		ITexture texture = resManager.loadTexture(resourcePath);
		cache.put(resourcePath, texture);
		return texture;
	}

	@Override
	public IMusic loadMusic(String resourcePath) {
		Object cacheVal = cache.get(resourcePath);
		if (cacheVal != null)
			return (IMusic) cacheVal;

		IMusic music = resManager.loadMusic(resourcePath);
		cache.put(resourcePath, music);
		return music;
	}

	@Override
	public ISound loadSound(String resourcePath) {
		Object cacheVal = cache.get(resourcePath);
		if (cacheVal != null)
			return (ISound) cacheVal;

		ISound sound = resManager.loadSound(resourcePath);
		cache.put(resourcePath, sound);
		return sound;
	}

	@Override
	public IVideo loadVideo(String resourcePath) {
		Object cacheVal = cache.get(resourcePath);
		if (cacheVal != null)
			return (IVideo) cacheVal;

		IVideo video = resManager.loadVideo(resourcePath);
		cache.put(resourcePath, video);
		return video;
	}
}
