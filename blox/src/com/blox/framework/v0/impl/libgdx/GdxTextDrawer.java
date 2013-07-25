package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.ITextDrawer;

class GdxTextDrawer implements ITextDrawer {
	private IFont font;

	GdxTextDrawer() {
		font = new GdxFont();
	}

	@Override
	public void draw(String text, float x, float y) {
		BitmapFont gdxFont = ((GdxFont) font).font;
		gdxFont.draw(GdxGame.spriteBatch, text, x, y);
	}

	@Override
	public void setFont(IFont font) {
		this.font = font;
	}

	@Override
	public IFont getFont() {
		return font;
	}
}
