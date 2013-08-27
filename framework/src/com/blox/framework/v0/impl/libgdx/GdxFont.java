package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class GdxFont implements IFont {
	BitmapFont font;
	private Vector size;

	GdxFont(BitmapFont font) {
		this.font = font;
		this.size = new Vector();
		Game.registerDisposable(this);
	}

	@Override
	public void setColor(Color color) {
		font.setColor(color.r / 255f, color.g / 255, color.b / 255f, color.a / 255f);
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	@Override
	public Vector getSize(String text) {
		TextBounds bounds = font.getBounds(text);
		size.x = bounds.width;
		size.y = bounds.height;
		return size;
	}

	@Override
	public void draw(String text, float x, float y) {
		float dx = Game.viewportToScreenX(Game.renderingShiftX);
		float dy = Game.viewportToScreenX(Game.renderingShiftY);
		
		com.badlogic.gdx.graphics.Color fontColor = font.getColor();
		font.setColor(fontColor.r, fontColor.g, fontColor.b, Game.renderingAlpha);
		font.drawMultiLine(GdxGame.spriteBatch, text, x + dx, y + dy);
	}
}
