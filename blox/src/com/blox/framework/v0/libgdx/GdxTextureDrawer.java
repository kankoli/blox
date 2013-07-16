package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class GdxTextureDrawer implements IDrawer {
	private Texture texture;

	GdxTextureDrawer(Texture texture) {
		this.texture = texture;
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

		GdxGame.spriteBatch
				.draw(texture, Game.descale(l.x), Game.descale(l.y),
						Game.descale(r.origin.x), Game.descale(r.origin.y),
						Game.descale(width), Game.descale(height), s.x,
						s.y, Game.descale(r.rotation.z), 0, 0,
						(int) Game.descale(width), (int) Game.descale(height),
						flipX, flipY);
	}
}
