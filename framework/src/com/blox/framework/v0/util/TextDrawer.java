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
		float dx = Game.scale(Game.renderingShiftX);
		float dy = Game.scale(Game.renderingShiftY);
		
		font.draw(text, x + dx, y + dy);
	}

	public static void draw(IFont font, String text) {
		Vector size = font.measureText(text);
		draw(font, text, (Game.getScreenWidth() - size.x) / 2, (Game.getScreenHeight() + size.y) / 2);
	}

	public static void draw(IFont font, String text, IDrawingInfo info) {
		draw(font, text, info, AlignCentered);
	}

	public static void draw(IFont font, String text, int align) {
		draw(font, text, 0, 0, Game.getScreenWidth(), Game.getScreenHeight(), align);
	}

	public static void draw(IFont font, String text, IDrawingInfo info, int align) {
		float scale = info.ignoreViewportScaling() ? 1f : Game.getScale();
		float offsetX = info.ignoreViewportOffset() ? 0f : Game.getViewportOffsetX();
		float offsetY = info.ignoreViewportOffset() ? 0f : Game.getViewportOffsetY();

		Vector loc = info.getLocation();

		float lx = scale * loc.x + offsetX;
		float ly = scale * loc.y + offsetY;
		float width = scale * info.getWidth();
		float height = scale * info.getHeight();
		
		font.getColor().set(info.getColor());

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
