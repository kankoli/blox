package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;

class GdxTexture implements ITexture {
	final Texture texture;

	GdxTexture(Texture texture) {
		this.texture = texture;
		Game.registerDisposable(this);
	}

	@Override
	public void draw(IDrawable drawable) {
		GdxTextureDrawer.instance.draw(texture, drawable);
	}
	
	@Override
	public void dispose() {
		if (texture != null)
			texture.dispose();
	}
}