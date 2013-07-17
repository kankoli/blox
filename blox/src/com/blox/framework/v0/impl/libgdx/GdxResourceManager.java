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
	public IMusic loadMusic(String resourcePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISound loadSound(String resourcePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVideo loadVideo(String resourcePath) {
		// TODO Auto-generated method stub
		return null;
	}

}
