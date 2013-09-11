package com.blox.framework.v0.util;

import com.blox.framework.v0.IShapeRenderer;

public final class ShapeRenderer {
	private final static IShapeRenderer renderer;

	private ShapeRenderer() {
		
	}
	
	static {
		renderer = Game.getShapeRenderer();
	}

	public static void drawLine(float x0, float y0, float x1, float y1, float lineWidth, Color color) {
		renderer.drawLine(
				Game.viewportToScreenX(Game.renderingShiftX + x0),
				Game.viewportToScreenY(Game.renderingShiftY + y0),
				Game.viewportToScreenX(Game.renderingShiftX + x1),
				Game.viewportToScreenY(Game.renderingShiftY + y1),
				Game.scale(lineWidth),
				color);
	}
}
