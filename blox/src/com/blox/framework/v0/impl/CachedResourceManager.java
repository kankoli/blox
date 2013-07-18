package com.blox.framework.v0.impl;

import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.IVideo;
import com.blox.framework.v0.util.Cache;

public class CachedResourceManager implements IResourceManager {
	private Cache cache;

	private IResourceLoader<ITexture> textureLoader;
	private IResourceLoader<ISound> soundLoader;
	private IResourceLoader<IMusic> musicLoader;
	private IResourceLoader<IVideo> videoLoader;

	public CachedResourceManager(final IResourceManager resManager) {
		this.cache = new Cache();

		this.textureLoader = new IResourceLoader<ITexture>() {
			public ITexture load(String resourcePath) {
				return resManager.loadTexture(resourcePath);
			}
		};

		this.soundLoader = new IResourceLoader<ISound>() {
			public ISound load(String resourcePath) {
				return resManager.loadSound(resourcePath);
			}
		};

		this.musicLoader = new IResourceLoader<IMusic>() {
			public IMusic load(String resourcePath) {
				return resManager.loadMusic(resourcePath);
			}
		};

		this.videoLoader = new IResourceLoader<IVideo>() {
			public IVideo load(String resourcePath) {
				return resManager.loadVideo(resourcePath);
			}
		};
	}

	@Override
	public ITexture loadTexture(String resourcePath) {
		return loadResource(resourcePath, textureLoader);
	}

	@Override
	public ISound loadSound(String resourcePath) {
		return loadResource(resourcePath, soundLoader);
	}

	@Override
	public IMusic loadMusic(String resourcePath) {
		return loadResource(resourcePath, musicLoader);
	}

	@Override
	public IVideo loadVideo(String resourcePath) {
		return loadResource(resourcePath, videoLoader);
	}

	@SuppressWarnings("unchecked")
	private <T> T loadResource(String resourcePath, IResourceLoader<T> loader) {
		Object resource = cache.get(resourcePath);
		if (resource != null)
			return (T) resource;

		resource = loader.load(resourcePath);
		cache.put(resourcePath, resource);
		return (T) resource;
	}

	private interface IResourceLoader<T> {
		T load(String resourcePath);
	}
}
