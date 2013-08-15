package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class GdxTextureDrawer {
	final static GdxTextureDrawer instance = new GdxTextureDrawer();

	private GdxTextureDrawer() {

	}

	public void draw(Texture texture, IDrawable drawinfo) {
		
		float scale = drawinfo.ignoreViewportScaling() ? 1f : Game.getScale();
		float offsetX = drawinfo.ignoreViewportOffset() ? 0f : Game.getViewportOffsetX();
		float offsetY = drawinfo.ignoreViewportOffset() ? 0f : Game.getViewportOffsetY();
		
		Vector l = drawinfo.getLocation();
		Rotation r = drawinfo.getRotation();
		Vector s = drawinfo.getScale();
		
		GdxGame.spriteBatch.draw(texture,
				scale * l.x + offsetX, 
				scale * l.y + offsetY,
				scale * (r.origin.x - l.x), 
				scale * (r.origin.y - l.y), 
				scale * drawinfo.getWidth(), 
				scale * drawinfo.getHeight(),
				s.x, s.y, r.rotation.z, 0, 0,
				texture.getWidth(),
				texture.getHeight(),
				drawinfo.isFlipX(),
				drawinfo.isFlipY());
	}
}
