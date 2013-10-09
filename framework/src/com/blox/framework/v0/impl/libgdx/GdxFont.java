package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

class GdxFont implements IFont {
	private final Vector size;
	private float scale;
	private final Color color;
	private BitmapFont font;

	GdxFont(BitmapFont font) {
		this.font = font;
		this.size = new Vector();
		this.color = Color.white();
		this.scale = 1.0f;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	@Override
	public Vector measureText(String text) {
		if (text == null) {
			size.x = size.y = 0;
		}
		else {
			font.setScale(scale);
			TextBounds bounds = font.getMultiLineBounds(text);
			size.x = bounds.width;
			size.y = bounds.height;
		}
		return size;
	}

	@Override
	public void draw(String text, float x, float y) {
		if (text == null)
			return;
		font.setScale(scale);
		font.setColor(color.r, color.g, color.b, color.a * Game.renderingAlpha);
		font.drawMultiLine(GdxGame.spriteBatch, text, x + Game.getRenderingShiftX(), y + Game.getRenderingShiftY(), measureText(text).x, HAlignment.CENTER);
	}

	@Override
	public void setScale(float scale) {
		this.scale = Game.scale(scale);
	}
}