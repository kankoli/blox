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

	public void draw(TextureRegion textureRegion , IDrawable drawinfo) {

		float scale = drawinfo.ignoreViewportScaling() ? 1f : Game.getScale();
		float offsetX = drawinfo.ignoreViewportOffset() ? 0f : Game.getViewportOffsetX();
		float offsetY = drawinfo.ignoreViewportOffset() ? 0f : Game.getViewportOffsetY();

		Vector l = drawinfo.getLocation();
		Rotation r = drawinfo.getRotation();
		Vector s = drawinfo.getScale();

		textureRegion.flip(textureRegion.isFlipX() != drawinfo.isFlipX(), textureRegion.isFlipY() != drawinfo.isFlipY());

		GdxGame.spriteBatch.draw(textureRegion,
				scale * l.x + offsetX, 
				scale * l.y + offsetY, 
				scale * (r.origin.x - l.x), 
				scale * (r.origin.y - l.y), 
				scale * drawinfo.getWidth(),
				scale * drawinfo.getHeight(), 
				s.x, s.y, r.rotation.z);
	}
}