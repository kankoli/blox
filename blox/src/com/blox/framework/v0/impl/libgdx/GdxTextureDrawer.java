package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class GdxTextureDrawer implements IDrawer {
	private final static GdxTextureDrawer instance = new GdxTextureDrawer();

	private GdxTextureDrawer() {

	}

	static GdxTextureDrawer getInstance() {
		return instance;
	}

	private Texture texture;

	void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public void draw(IDrawable drawable) {
		
		float scale = drawable.ignoreViewportScaling() ? 1f : Game.getScale();
		float offsetX = drawable.ignoreViewportOffset() ? 0f : Game.getViewportOffsetX();
		float offsetY = drawable.ignoreViewportOffset() ? 0f : Game.getViewportOffsetY();
		
		Vector l = drawable.getLocation();
		Rotation r = drawable.getRotation();
		float width = drawable.getWidth();
		float height = drawable.getHeight();
		Vector s = drawable.getScale();
		boolean flipX = drawable.isFlipX();
		boolean flipY = drawable.isFlipY();

		GdxGame.spriteBatch.draw(texture,
				scale * l.x + offsetX, 
				scale * l.y + offsetY,
				scale * (r.origin.x - l.x), 
				scale * (r.origin.y - l.y), 
				scale * width, 
				scale * height, 
				s.x, s.y, r.rotation.z, 0, 0, 
				(int) (scale * width),
				(int) (scale * height), 
				flipX, flipY);
	}
}
