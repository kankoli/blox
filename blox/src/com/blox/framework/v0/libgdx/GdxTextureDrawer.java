package com.blox.framework.v0.libgdx;

import com.blox.framework.v0.DrawOptions;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;

class GdxTextureDrawer implements IDrawer {
	GdxTextureDrawer() {

	}

	@Override
	public void draw(IDrawable drawable, DrawOptions options) {
		GdxTexture texture = (GdxTexture) drawable;
		GdxGame.spriteBatch.draw(texture.texture, options.x, options.y,
				options.originX, options.originY, options.width,
				options.height, options.scaleX, options.scaleY,
				options.rotation, 0, 0, 48, 48, options.flipX, options.flipY);
	}
}
