package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.ITexture;

class GdxTextureRegion implements ITexture, IGdxTexture {
	final TextureRegion textureRegion;

	GdxTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isRegion() {
		return true;
	}

	@Override
	public ITexture[] split(int width, int height) {
		return null;
	}
}
