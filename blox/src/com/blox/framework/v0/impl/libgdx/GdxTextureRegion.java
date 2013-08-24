package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.ITexture;

class GdxTextureRegion implements ITexture {
	private final TextureRegion textureRegion;

	GdxTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}

	@Override
	public void draw(IDrawable drawable) {
		GdxTextureRegionDrawer.instance.draw(textureRegion, drawable);
	}

	@Override
	public void dispose() {

	}
}
