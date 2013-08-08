package com.blox.framework.v0.impl;

import java.io.InputStream;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.IVideo;
import com.blox.framework.v0.util.Cache;

public class CachedResourceManager implements IResourceManager {
	private Cache cache;
	
	private IResourceManager resManager;
	
	private IResourceLoader<ITexture> textureLoader;
	private IResourceLoader<ISound> soundLoader;
	private IResourceLoader<IMusic> musicLoader;
	private IResourceLoader<IVideo> videoLoader;
	private IResourceLoader<IFont> fontLoader;

	public CachedResourceManager(final IResourceManager resManager) {
		this.cache = new Cache();

		this.textureLoader = new IResourceLoader<ITexture>() {
			@Override
			public ITexture load(String resourcePath) {
				return resManager.loadTexture(resourcePath);
			}
		};

		this.soundLoader = new IResourceLoader<ISound>() {
			@Override
			public ISound load(String resourcePath) {
				return resManager.loadSound(resourcePath);
			}
		};

		this.musicLoader = new IResourceLoader<IMusic>() {
			@Override
			public IMusic load(String resourcePath) {
				return resManager.loadMusic(resourcePath);
			}
		};

		this.videoLoader = new IResourceLoader<IVideo>() {
			@Override
			public IVideo load(String resourcePath) {
				return resManager.loadVideo(resourcePath);
			}
		};

		this.fontLoader = new IResourceLoader<IFont>() {
			@Override
			public IFont load(String resourcePath) {
				return resManager.loadFont(resourcePath);
			}
		};
	
		this.resManager = resManager;
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

	@Override
	public IFont loadFont(String resourcePath) {
		return loadResource(resourcePath, fontLoader);
	}

	@Override
	public InputStream readFile(String resourcePath) {
		return resManager.readFile(resourcePath);
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
