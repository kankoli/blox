package com.blox.framework.v0.impl;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Cache;

public class CachedResourceManager implements IResourceManager {
	private Cache cache;

	private final IResourceManager manager;
	
	private IResourceLoader<ITexture> textureLoader;
	private IResourceLoader<ISound> soundLoader;
	private IResourceLoader<IMusic> musicLoader;

	public CachedResourceManager(final IResourceManager resManager) {
		this.cache = new Cache();
		this.manager = resManager;

		this.textureLoader = new IResourceLoader<ITexture>() {
			@Override
			public ITexture load(String resourcePath) {
				return resManager.getTexture(resourcePath);
			}
		};

		this.soundLoader = new IResourceLoader<ISound>() {
			@Override
			public ISound load(String resourcePath) {
				return resManager.getSound(resourcePath);
			}
		};

		this.musicLoader = new IResourceLoader<IMusic>() {
			@Override
			public IMusic load(String resourcePath) {
				return resManager.getMusic(resourcePath);
			}
		};
	}

	@Override
	public ITexture getTexture(String resourcePath) {
		return loadResource(resourcePath, textureLoader);
	}

	@Override
	public ISound getSound(String resourcePath) {
		return loadResource(resourcePath, soundLoader);
	}

	@Override
	public IMusic getMusic(String resourcePath) {
		return loadResource(resourcePath, musicLoader);
	}

	@Override
	public boolean isLoading() {
		return manager.isLoading();
	}

	@Override
	public int getLoadingPercent() {
		return manager.getLoadingPercent();
	}

	@Override
	public void beginLoading() {
		manager.beginLoading();
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

	@Override
	public IFont getFont(String id) {
		return manager.getFont(id);
	}
}
