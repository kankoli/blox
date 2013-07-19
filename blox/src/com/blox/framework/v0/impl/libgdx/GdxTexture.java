package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;

class GdxTexture implements ITexture {
	Texture texture;
	
	GdxTexture(Texture texture) {
		this.texture = texture;
		Game.getDisposeManager().register(this);
	}

	@Override
	public void draw(IDrawable drawable) {
		GdxTextureDrawer drawer = GdxTextureDrawer.getInstance(); 
		drawer.setTexture(texture);
		drawer.draw(drawable);
	}

	@Override
	public void dispose() {
		if (texture != null)
			texture.dispose();
	}
}