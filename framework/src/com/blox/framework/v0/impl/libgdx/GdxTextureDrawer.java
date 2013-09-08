package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.ITextureDrawer;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

final class GdxTextureDrawer implements ITextureDrawer {
	GdxTextureDrawer() {

	}

	@Override
	public void draw(ITexture texture, IDrawingInfo info) {
		Color c = info.getColor();
		GdxGame.spriteBatch.setColor(c.r, c.g, c.b, c.a * Game.renderingAlpha);
		
		if (((IGdxTexture)texture).isRegion())
			drawTextureRegion(((GdxTextureRegion)texture).textureRegion, info);
		else 
			drawTexture(((GdxTexture)texture).texture, info);
	}

	private static void drawTexture(Texture texture, IDrawingInfo info) {
		Vector l = info.getLocation();
		Rotation r = info.getRotation();
		Vector s = info.getScale();

		GdxGame.spriteBatch.draw(texture,
				l.x, l.y,
				r.origin.x, r.origin.y,
				info.getWidth(), info.getHeight(),
				s.x, s.y, r.rotation.z, 0, 0,
				texture.getWidth(), texture.getHeight(),
				info.isFlipX(), info.isFlipY());
	}

	private static void drawTextureRegion(TextureRegion textureRegion, IDrawingInfo info) {
		Vector l = info.getLocation();
		Rotation r = info.getRotation();
		Vector s = info.getScale();

		textureRegion.flip(textureRegion.isFlipX() != info.isFlipX(), textureRegion.isFlipY() != info.isFlipY());
		
		GdxGame.spriteBatch.draw(textureRegion,
				l.x, l.y, 
				r.origin.x, r.origin.y, 
				info.getWidth(), info.getHeight(), 
				s.x, s.y, r.rotation.z);
	}
}
