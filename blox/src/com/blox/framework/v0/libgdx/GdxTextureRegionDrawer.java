package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class GdxTextureRegionDrawer implements IDrawer {
	private TextureRegion textureRegion;

	GdxTextureRegionDrawer(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}

	@Override
	public void draw(IDrawable drawable) {
		Vector l = drawable.getLocation();
		Rotation r = drawable.getRotation();
		float width = drawable.getWidth();
		float height = drawable.getHeight();
		Vector s = drawable.getScale();
		boolean flipX = drawable.isFlipX();
		boolean flipY = drawable.isFlipY();

		textureRegion.flip(textureRegion.isFlipX() != flipX,
				textureRegion.isFlipY() != flipY);

		GdxGame.spriteBatch.draw(textureRegion, Game.descale(l.x),
				Game.descale(l.y), Game.descale(r.origin.x),
				Game.descale(r.origin.y), Game.descale(width), Game.descale(height),
				s.x, s.y, Game.descale(r.rotation.z));
	}
}