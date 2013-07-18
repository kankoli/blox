package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.IVideo;

class GdxResourceManager implements IResourceManager {
	GdxResourceManager() {
		
	}
	
	@Override
	public ITexture loadTexture(String resourcePath) {
		Texture texture = new Texture(Gdx.files.internal(resourcePath));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return new GdxTexture(texture);
	}

	@Override
	public ISound loadSound(String resourcePath) {		
		throw new UnsupportedOperationException("loadSound");
	}

	@Override
	public IMusic loadMusic(String resourcePath) {
		throw new UnsupportedOperationException("loadMusic");
	}

	@Override
	public IVideo loadVideo(String resourcePath) {
		throw new UnsupportedOperationException("loadVideo");
	}
}
