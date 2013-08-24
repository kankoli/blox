package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class GdxTextureRegionDrawer {
	final static GdxTextureRegionDrawer instance = new GdxTextureRegionDrawer();

	private GdxTextureRegionDrawer() {

	}

	public void draw(TextureRegion textureRegion , IDrawable drawable) {

		float scale = drawable.ignoreViewportScaling() ? 1f : Game.getScale();
		float offsetX = drawable.ignoreViewportOffset() ? 0f : Game.getViewportOffsetX();
		float offsetY = drawable.ignoreViewportOffset() ? 0f : Game.getViewportOffsetY();

		Vector l = drawable.getLocation();
		Rotation r = drawable.getRotation();
		Vector s = drawable.getScale();

		textureRegion.flip(textureRegion.isFlipX() != drawable.isFlipX(), textureRegion.isFlipY() != drawable.isFlipY());

		GdxGame.spriteBatch.setColor(1, 1, 1, Game.renderingAlpha);
		
		GdxGame.spriteBatch.draw(textureRegion,
				scale * l.x + offsetX, 
				scale * l.y + offsetY, 
				scale * (r.origin.x - l.x), 
				scale * (r.origin.y - l.y), 
				scale * drawable.getWidth(),
				scale * drawable.getHeight(), 
				s.x, s.y, r.rotation.z);
	}
}