package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.util.Texture;

class GdxTextureRegion extends Texture {
	GdxTextureRegion(TextureRegion textureRegion) {
		super(new GdxTextureRegionDrawer(textureRegion));
	}
}
