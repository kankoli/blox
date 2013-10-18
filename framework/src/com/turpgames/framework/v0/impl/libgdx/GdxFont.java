package com.turpgames.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.turpgames.framework.v0.IFont;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Rectangle;
import com.turpgames.framework.v0.util.Vector;

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
	public Vector measureText(String text, float maxWidth) {
		if (text == null) {
			size.x = size.y = 0;
		}
		else {
			font.setScale(scale);
			TextBounds bounds = font.getWrappedBounds(text, maxWidth);
			size.x = bounds.width;
			size.y = bounds.height;
		}
		return size;
	}

	@Override
	public void draw(String text, Rectangle textArea, int align) {
		if (text == null)
			return;
		font.setScale(scale);
		font.setColor(color.r, color.g, color.b, color.a * Game.renderingAlpha);
		font.drawWrapped(GdxGame.spriteBatch, text, textArea.x, textArea.y + textArea.height, textArea.width, getHAlignment(align));
	}

	@Override
	public void setScale(float scale) {
		this.scale = Game.scale(scale);
	}

	private static HAlignment getHAlignment(int align) {
		switch (align) {
		case Text.HAlignLeft:
			return HAlignment.LEFT;
		case Text.HAlignRight:
			return HAlignment.RIGHT;
		case Text.HAlignCenter:
			return HAlignment.CENTER;
		default:
			return HAlignment.LEFT;
		}
	}
}