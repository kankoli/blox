package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.impl.Texture;

class GdxTextureRegion extends Texture {
	GdxTextureRegion(TextureRegion textureRegion) {
		super(new GdxTextureRegionDrawer(textureRegion));
	}
}
