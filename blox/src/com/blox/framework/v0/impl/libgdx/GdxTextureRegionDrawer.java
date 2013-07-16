package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.ToolBox;
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

		GdxGame.spriteBatch.draw(textureRegion, ToolBox.descale(l.x),
				ToolBox.descale(l.y), ToolBox.descale(r.origin.x),
				ToolBox.descale(r.origin.y), ToolBox.descale(width), ToolBox.descale(height),
				s.x, s.y, ToolBox.descale(r.rotation.z));
	}
}