package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.DrawOptions;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.ITexture;

class GdxTexture implements ITexture {
	private IDrawer drawer;

	Texture texture;

	GdxTexture(Texture texture) {
		this.texture = texture;
		this.drawer = new GdxTextureDrawer();
	}

	@Override
	public void draw(DrawOptions options) {
		drawer.draw(this, options);
	}

	@Override
	public int getWidth() {
		return texture.getWidth();
	}

	@Override
	public int getHeight() {
		return texture.getHeight();
	}
}