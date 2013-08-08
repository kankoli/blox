package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class GdxFont implements IFont {
	BitmapFont font;

	GdxFont() {
		this(new BitmapFont());
	}

	GdxFont(String fontFile) {
		this(new BitmapFont(Gdx.files.internal(fontFile), false));
	}

	GdxFont(BitmapFont font) {
		this.font = font;
		Game.getDisposeManager().register(this);
	}

	@Override
	public void setScale(float scale) {
		font.setScale(Game.scale(scale));
	}

	@Override
	public void setColor(Color color) {
		font.setColor(color.r / 255f, color.g / 255, color.b / 255f, color.a / 255f);
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	private Vector size = new Vector();

	@Override
	public Vector calculateSize(String text) {
		TextBounds bounds = font.getBounds(text);
		size.x = bounds.width;
		size.y = bounds.height;
		return size;
	}

	@Override
	public float getLineHeight() {
		return font.getLineHeight();
	}
}
