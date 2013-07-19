package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.ITextDrawer;
import com.blox.framework.v0.util.Game;

class GdxTextDrawer implements ITextDrawer {
	private IFont font;
	
	GdxTextDrawer() {
		font = new GdxFont();
	}
	
	public void draw(String text, float x, float y) {
		BitmapFont gdxFont = ((GdxFont)font).font;
		gdxFont.draw(GdxGame.spriteBatch, text, Game.world.descale(x), Game.world.descale(y));
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
