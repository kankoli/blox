package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;

class GdxTexture extends com.blox.framework.v0.impl.Texture {
	Texture texture;
	
	GdxTexture(Texture texture) {
		super(new GdxTextureDrawer(texture));
		this.texture = texture;
	}
}