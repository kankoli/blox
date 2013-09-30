package com.blox.framework.v0.util;

import com.blox.framework.v0.IDrawingInfo;
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

	public static int getAlignment(String key) {
		key = key.toUpperCase();

		if ("N".equals(key))
			return AlignN;
		if ("NE".equals(key))
			return AlignNE;
		if ("E".equals(key))
			return AlignE;
		if ("SE".equals(key))
			return AlignSE;
		if ("S".equals(key))
			return AlignS;
		if ("SW".equals(key))
			return AlignSW;
		if ("W".equals(key))
			return AlignW;
		if ("NW".equals(key))
			return AlignNW;
		if ("CENTERED".equals(key))
			return AlignCentered;

		return AlignW;
	}

	public static void draw(IFont font, String text, float x, float y) {
		font.draw(text, x, y);
	}

	public static void draw(IFont font, String text) {
		draw(font, text, AlignCentered);
	}

	public static void draw(IFont font, String text, IDrawingInfo info) {
		draw(font, text, info, AlignCentered);
	}

	public static void draw(IFont font, String text, int align) {
		draw(font, text, IDrawingInfo.screen, align);
	}

	public static void draw(IFont font, String text, IDrawingInfo info, int align) {
		Vector loc = info.getLocation();

		float lx = loc.x;
		float ly = loc.y;
		float width = info.getWidth();
		float height = info.getHeight();

		if (!info.ignoreViewport()) {
			lx = Game.viewportToScreenX(lx);
			ly = Game.viewportToScreenY(ly);
			width = Game.scale(width);
			height = Game.scale(height);
		}
		
		draw(font, text, lx, ly, width, height, align);
	}

	private static void draw(IFont font, String text, float x, float y, float width, float height, int align) {
		Vector size = font.measureText(text);

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
