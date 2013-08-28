package com.blox.framework.v0.impl.libgdx;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
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
import com.blox.framework.v0.IResourceInitListener;
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
		textureParams.minFilter = TextureFilter.Linear;
		textureParams.magFilter = TextureFilter.Linear;
		
		SoundParameter soundParam = new SoundParameter();
		
		MusicParameter musicParam = new MusicParameter();
		
		BitmapFontParameter fontParams = new BitmapFontParameter();
		fontParams.maxFilter = TextureFilter.Linear;
		fontParams.minFitler = TextureFilter.Linear;
		
		resourceTypes.put("texture", new ResourceLoaderInfo<Texture>(Texture.class, textureParams));
		resourceTypes.put("sound",  new ResourceLoaderInfo<Sound>(Sound.class, soundParam));
		resourceTypes.put("music",  new ResourceLoaderInfo<Music>(Music.class, musicParam));
		resourceTypes.put("font",  new ResourceLoaderInfo<BitmapFont>(BitmapFont.class, fontParams));
	}
	
	private static class ResourceLoaderInfo<T> {
		private Class<T> clazz;
		private AssetLoaderParameters<T> params;
		private ResourceLoaderInfo(Class<T> clazz, AssetLoaderParameters<T> params) {
			this.clazz = clazz;
			this.params = params;
		}		
	}

	private final AssetManager manager = new AssetManager();
	private ResourcesMetadata resources;

	GdxResourceManager() {

	}

	@Override
	public ITexture getTexture(String id) {
		ResourceMetadata meta = resources.getTexture(id);
		Texture texture = manager.get(meta.getPath());
		// Texture texture = new Texture(Gdx.files.internal(meta.getPath()));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return new GdxTexture(texture);
	}

	@Override
	public IFont getFont(String id, int size) {
		throw new UnsupportedOperationException("getFont");
	}

	@Override
	public ISound getSound(String id) {
		throw new UnsupportedOperationException("getSound");
	}

	@Override
	public IMusic getMusic(String id) {
		throw new UnsupportedOperationException("getMusic");
	}

	@Override
	public void init(final IResourceInitListener listener) {
		Game.registerDisposable(this);

		resources = GameMetadata.getResources();
		
		final int totalResourceCount = resources.getAll().size();
		
		AssetLoaderParameters.LoadedCallback callback = new AssetLoaderParameters.LoadedCallback() {
			private int totalCount = totalResourceCount;
			@SuppressWarnings("rawtypes")
			public void finishedLoading(AssetManager assetManager, String fileName, Class type) {
				if (--totalCount == 0)
					listener.resourcesInited();
			}
		};
		
		manager.setErrorListener(new AssetErrorListener() {			
			@Override
			public void error(String fileName, Class type, Throwable throwable) {
				System.out.println("error: " + fileName + "\n" + throwable);
			}
		});
		
		manager.setLoader(BitmapFont.class, new TtfFontLoader());

		for (ResourceMetadata resourceMeta : resources.getAll().values()) {
			ResourceLoaderInfo info = resourceTypes.get(resourceMeta.getType());
			info.params.loadedCallback = callback;
			manager.load(resourceMeta.getPath(), info.clazz, info.params);
		}
		
		if (manager.update())
			listener.resourcesInited();
	}

	@Override
	public void dispose() {
		manager.dispose();
	}

	private class ResourceLoadingCompleteHandler {
		private int totalResourceCount;
		private IResourceInitListener listener;
		
		private ResourceLoadingCompleteHandler(int totalCount, IResourceInitListener listener) {
			totalResourceCount = totalCount;
			this.listener = listener;
		}
		
		private void complete() {
			if (--totalResourceCount == 0)
				listener.resourcesInited();
		}
	}
}
