package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.blox.framework.v0.IShapeRenderer;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;

final class GdxShapeRenderer implements IShapeRenderer {
	private ShapeRenderer renderer;

	GdxShapeRenderer() {
		renderer = GdxGame.shapeRenderer;
	}

	@Override
	public void drawLine(float x0, float y0, float x1, float y1, Color color) {
		begin(ShapeType.Line, color);
		renderer.line(x0, y0, x1, y1);
		end();
	}

	@Override
	public void drawRect(float x, float y, float w, float h, Color color, boolean filled) {
		if (filled)
			begin(ShapeType.FilledRectangle, color);
		else
			begin(ShapeType.Rectangle, color);
		
		if (filled)
			renderer.filledRect(x, y, w, h);
		else
			renderer.rect(x, y, w, h);
		
		end();
	}

	private void end() {
		renderer.end();
		GdxGame.spriteBatch.begin();
	}

	private void begin(ShapeType shapeType, Color color) {
		GdxGame.spriteBatch.end();
		renderer.begin(shapeType);
		renderer.setColor(color.r, color.g, color.b, color.a * Game.renderingAlpha);
	}
}
