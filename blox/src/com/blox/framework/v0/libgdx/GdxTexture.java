package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.Texture;

class GdxTexture extends com.blox.framework.v0.util.Texture {
	Texture texture;
	
	GdxTexture(Texture texture) {
		super(new GdxTextureDrawer(texture));
		this.texture = texture;
	}
}