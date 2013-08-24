package com.blox.framework.v0.util;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IFont;

public final class TextDrawer {
	private static final int n = 1 << 0;
	private static final int e = 1 << 1;
	private static final int s = 1 << 2;
	private static final int w = 1 << 3;

	public static final int AlignN = n;
	public static final int AlignNE = n | e;
	public static final int AlignE = e;
	public static final int AlignSE = s | e;
	public static final int AlignS = s;
	public static final int AlignSW = s | w;
	public static final int AlignW = w;
	public static final int AlignNW = n | w;
	public static final int AlignCentered = 0;

	private TextDrawer() {

	}

	public static void draw(IFont font, String text, float x, float y) {
		font.draw(text, x, y);
	}

	public static void draw(IFont font, String text) {
		Vector size = font.getSize(text);
		draw(font, text, (Game.getScreenWidth() - size.x) / 2, (Game.getScreenHeight() + size.y) / 2);
	}

	public static void draw(IFont font, String text, IDrawable drawable) {
		draw(font, text, drawable, AlignCentered);
	}

	public static void draw(IFont font, String text, int align) {
		draw(font, text, 0, 0, Game.getScreenWidth(), Game.getScreenHeight(), align);
	}

	public static void draw(IFont font, String text, IDrawable drawable, int align) {
		float scale = drawable.ignoreViewportScaling() ? 1f : Game.getScale();
		float offsetX = drawable.ignoreViewportOffset() ? 0f : Game.getViewportOffsetX();
		float offsetY = drawable.ignoreViewportOffset() ? 0f : Game.getViewportOffsetY();

		Vector loc = drawable.getLocation();

		float lx = scale * loc.x + offsetX;
		float ly = scale * loc.y + offsetY;
		float width = scale * drawable.getWidth();
		float height = scale * drawable.getHeight();

		draw(font, text, lx, ly, width, height, align);
	}

	private static void draw(IFont font, String text, float x, float y, float width, float height, int align) {
		Vector size = font.getSize(text);

		if ((align & e) == e)
			x = x + width - size.x;
		else if ((align & w) != w)
			x = x + (width - size.x) / 2;

		if ((align & n) == n)
			y = y + height;
		else if ((align & s) == s)
			y = y + size.y;
		else
			y = y + (height + size.y) / 2;

		draw(font, text, x, y);
	}
}
