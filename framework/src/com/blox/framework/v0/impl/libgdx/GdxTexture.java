package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;

class GdxTexture implements ITexture, IGdxTexture {
	final Texture texture;

	GdxTexture(Texture texture) {
		this.texture = texture;
		Game.registerDisposable(this);
	}
	
	@Override
	public void dispose() {
		if (texture != null)
			texture.dispose();
	}

	@Override
	public boolean isRegion() {
		return false;
	}

	@Override
	public ITexture[] split(int width, int height) {
		return GdxTextureSplitter.split(texture, width, height);
	}
}