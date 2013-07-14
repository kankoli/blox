package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.DrawOptions;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.ITexture;

class GdxTextureRegion implements ITexture {
	private IDrawer drawer;
	
	TextureRegion textureRegion;

	GdxTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
		this.drawer = new GdxTextureRegionDrawer();
	}

	@Override
	public void draw(DrawOptions options) {
		drawer.draw(this, options);
	}

	@Override
	public int getWidth() {
		return textureRegion.getRegionWidth();
	}

	@Override
	public int getHeight() {
		return textureRegion.getRegionHeight();
	}
}
