package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class GdxTextureRegionDrawer implements IDrawer {
	private final static GdxTextureRegionDrawer instance = new GdxTextureRegionDrawer();

	private GdxTextureRegionDrawer() {

	}

	static GdxTextureRegionDrawer getInstance() {
		return instance;
	}

	private TextureRegion textureRegion;

	void setTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
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

		textureRegion.flip(textureRegion.isFlipX() != flipX, textureRegion.isFlipY() != flipY);

		GdxGame.spriteBatch.draw(textureRegion,
				scale * l.x + offsetX, 
				scale * l.y + offsetY, 
				scale * (r.origin.x - l.x),
				scale * (r.origin.y - l.y),
				scale * width,
				scale * height,
				s.x, s.y, r.rotation.z);
	}
}