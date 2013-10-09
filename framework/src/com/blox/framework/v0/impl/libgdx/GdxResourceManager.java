package com.blox.framework.v0.impl.libgdx;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.assets.loaders.MusicLoader.MusicParameter;
import com.badlogic.gdx.assets.loaders.SoundLoader.SoundParameter;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.blox.framework.v0.IDisposable;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.metadata.GameMetadata;
import com.blox.framework.v0.metadata.ResourceMetadata;
import com.blox.framework.v0.metadata.ResourcesMetadata;
import com.blox.framework.v0.util.Game;

class GdxResourceManager implements IResourceManager, IDisposable {

	private static final Map<String, ResourceLoaderInfo<?>> resourceTypes = new HashMap<String, ResourceLoaderInfo<?>>();

	static {
		TextureParameter textureParams = new TextureParameter();

		SoundParameter soundParam = new SoundParameter();

		MusicParameter musicParam = new MusicParameter();

		BitmapFontParameter fontParam = new BitmapFontParameter();
		fontParam.maxFilter = TextureFilter.Linear;
		fontParam.minFitler = TextureFilter.Linear;

		resourceTypes.put("texture", new ResourceLoaderInfo<Texture>(Texture.class, textureParams));
		resourceTypes.put("sound", new ResourceLoaderInfo<Sound>(Sound.class, soundParam));
		resourceTypes.put("music", new ResourceLoaderInfo<Music>(Music.class, musicParam));
		resourceTypes.put("font", new ResourceLoaderInfo<BitmapFont>(BitmapFont.class, fontParam));
	}

	private static class ResourceLoaderInfo<T> {
		private Class<T> clazz;
		private AssetLoaderParameters<T> params;

		private ResourceLoaderInfo(Class<T> clazz, AssetLoaderParameters<T> params) {
			this.clazz = clazz;
			this.params = params;
		}
	}

	private final AssetManager manager;
	private ResourcesMetadata resources;

	GdxResourceManager() {
		manager = new AssetManager();
	}

	@Override
	public ITexture getTexture(String id) {
		ResourceMetadata meta = resources.getTexture(id);
		Texture texture = manager.get(meta.getPath());
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return new GdxTexture(texture);
	}

	@Override
	public IFont getFont(String id) {
		ResourceMetadata meta = resources.getFont(id);
		BitmapFont font =  manager.get(meta.getPath());
		return new GdxFont(font);
	}

	@Override
	public ISound getSound(String id) {
		ResourceMetadata meta = resources.getSound(id);
		Sound sound = manager.get(meta.getPath());
		return new GdxSound(sound);
	}

	@Override
	public IMusic getMusic(String id) {
		ResourceMetadata meta = resources.getMusic(id);
		addToLoadQueue(meta);
		manager.finishLoading();
		Music music = manager.get(meta.getPath());
		return new GdxMusic(music, manager, meta.getPath());

	}

	@Override
	public boolean isLoading() {
		return !manager.update();
	}

	@Override
	public void beginLoading() {
		Game.registerDisposable(this);

		resources = GameMetadata.getResources();

		loadPrimaryResources();
		addResourcesToLoadQueue();
	}

	@Override
	public InputStream readFile(String path) {
		return Gdx.files.internal(path).read();
	}

	@Override
	public float getProgress() {
		return manager.getProgress();
	}

	@Override
	public void dispose() {
		manager.dispose();
	}

	private void addResourcesToLoadQueue() {
		for (ResourceMetadata resourceMeta : resources.getAll().values()) {
			if (manager.isLoaded(resourceMeta.getPath()) || resourceMeta.skip() || resourceMeta.isOnDemand())
				continue;
			addToLoadQueue(resourceMeta);
		}
	}

	private void loadPrimaryResources() {
		for (ResourceMetadata resourceMeta : resources.getAll().values()) {
			if (!resourceMeta.isPrimary())
				continue;
			addToLoadQueue(resourceMeta);
		}

		manager.finishLoading();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addToLoadQueue(ResourceMetadata resourceMeta) {
		ResourceLoaderInfo info = resourceTypes.get(resourceMeta.getType());
		manager.load(resourceMeta.getPath(), info.clazz, info.params);
	}
}
