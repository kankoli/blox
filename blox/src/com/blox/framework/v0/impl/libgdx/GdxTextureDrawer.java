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

	public void draw(Texture texture, IDrawable drawable) {
		
		float scale = drawable.ignoreViewportScaling() ? 1f : Game.getScale();
		float offsetX = drawable.ignoreViewportOffset() ? 0f : Game.getViewportOffsetX();
		float offsetY = drawable.ignoreViewportOffset() ? 0f : Game.getViewportOffsetY();
		
		Vector l = drawable.getLocation();
		Rotation r = drawable.getRotation();
		Vector s = drawable.getScale();
		
		GdxGame.spriteBatch.setColor(1, 1, 1, Game.renderingAlpha);
		
		float dx = Game.viewportToScreenX(Game.renderingShiftX);
		float dy = Game.viewportToScreenX(Game.renderingShiftY);
		
		GdxGame.spriteBatch.draw(texture,
				scale * (l.x + dx) + offsetX, 
				scale * (l.y + dy) + offsetY,
				scale * (r.origin.x - l.x), 
				scale * (r.origin.y - l.y), 
				scale * drawable.getWidth(), 
				scale * drawable.getHeight(),
				s.x, s.y, r.rotation.z, 0, 0,
				texture.getWidth(),
				texture.getHeight(),
				drawable.isFlipX(),
				drawable.isFlipY());
	}
}
