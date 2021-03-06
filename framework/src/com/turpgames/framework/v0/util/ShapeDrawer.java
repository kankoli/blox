package com.turpgames.framework.v0.util;

import com.turpgames.framework.v0.IDrawingInfo;
import com.turpgames.framework.v0.IShapeRenderer;

public final class ShapeDrawer {
	private final static IShapeRenderer renderer;

	private ShapeDrawer() {

	}

	static {
		renderer = Game.getShapeRenderer();
	}

	public static void drawRect(IDrawingInfo info, boolean filled) {
		Vector l = info.getLocation();
		drawRect(l.x, l.y, info.getWidth(), info.getHeight(), info.getColor(), filled, info.ignoreViewport());
	}
	
	public static void drawRect(IDrawingInfo info, float padX, float padY, boolean filled) {
		Vector l = info.getLocation();
		drawRect(l.x - padX, l.y - padY, info.getWidth() + 2 * padX, info.getHeight() + 2 * padY, info.getColor(), filled, info.ignoreViewport());
	}

	public static void drawRect(float x, float y, float w, float h, Color color, boolean filled, boolean ignoreViewport) {
		if (!ignoreViewport) {
			x = Game.viewportToScreenX(x);
			y = Game.viewportToScreenY(y);
			w = Game.scale(w);
			h = Game.scale(h);
		}

		renderer.drawRect(x + Game.getRenderingShiftX(), y + Game.getRenderingShiftY(), w, h, color, filled);
	}

	public static void drawLine(float x0, float y0, float x1, float y1, Color color, boolean ignoreViewport) {
		if (!ignoreViewport) {
			x0 = Game.viewportToScreenX(x0);
			y0 = Game.viewportToScreenY(y0);
			x1 = Game.viewportToScreenX(x1);
			y1 = Game.viewportToScreenY(y1);
		}
		renderer.drawLine(
				x0 + Game.getRenderingShiftX(),
				y0 + Game.getRenderingShiftY(),
				x1 + Game.getRenderingShiftX(),
				y1 + Game.getRenderingShiftY(),
				color);
	}
}
