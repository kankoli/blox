package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.metadata.GameMetadata;
import com.blox.framework.v0.metadata.TextureMetadata;

class GdxResourceManager implements IResourceManager {
	GdxResourceManager() {

	}

	@Override
	public ITexture loadTexture(String id) {
		TextureMetadata meta = GameMetadata.getTexture(id);
		Texture texture = new Texture(Gdx.files.internal(meta.getPath()));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return new GdxTexture(texture);
	}

	@Override
	public ISound loadSound(String id) {
		throw new UnsupportedOperationException("loadSound");
	}

	@Override
	public IMusic loadMusic(String id) {
		throw new UnsupportedOperationException("loadMusic");
	}
}
