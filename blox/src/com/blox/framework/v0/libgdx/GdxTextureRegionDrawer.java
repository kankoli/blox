package com.blox.framework.v0.libgdx;

import com.blox.framework.v0.DrawOptions;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;

class GdxTextureRegionDrawer implements IDrawer {
	GdxTextureRegionDrawer() {
		
	}
	
	@Override
	public void draw(IDrawable drawable, DrawOptions options) {
		GdxTextureRegion textureRegion = (GdxTextureRegion) drawable;

		textureRegion.textureRegion.flip(
				textureRegion.textureRegion.isFlipX() != options.flipX,
				textureRegion.textureRegion.isFlipY() != options.flipY);

		GdxGame.spriteBatch.draw(textureRegion.textureRegion, options.x,
				options.y, options.originX, options.originY, options.width,
				options.height, options.scaleX, options.scaleY,
				options.rotation);
	}
}