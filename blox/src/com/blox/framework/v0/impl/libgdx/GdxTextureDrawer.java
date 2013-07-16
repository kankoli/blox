package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.ToolBox;
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
				.draw(texture, ToolBox.descale(l.x), ToolBox.descale(l.y),
						ToolBox.descale(r.origin.x), ToolBox.descale(r.origin.y),
						ToolBox.descale(width), ToolBox.descale(height), s.x,
						s.y, ToolBox.descale(r.rotation.z), 0, 0,
						(int) ToolBox.descale(width), (int) ToolBox.descale(height),
						flipX, flipY);
	}
}
