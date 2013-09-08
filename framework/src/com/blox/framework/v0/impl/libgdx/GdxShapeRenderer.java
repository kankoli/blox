package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.blox.framework.v0.IShapeRenderer;
import com.blox.framework.v0.util.Color;

final class GdxShapeRenderer implements IShapeRenderer {
	private ShapeRenderer renderer;

	GdxShapeRenderer() {
		renderer = GdxGame.shapeRenderer;
	}
	
	@Override
	public void drawLine(float x0, float y0, float x1, float y1, float lineWidth, Color color) {
		begin(ShapeType.Line, lineWidth, color);
		renderer.line(x0, y0, x1, y1);
		end();
	}
	
	private void end() {
		renderer.end();
		GdxGame.spriteBatch.begin();
	}

	private void begin(ShapeType shapeType, float lineWidth, Color color) {
		GdxGame.spriteBatch.end();
		renderer.begin(shapeType);
		Gdx.gl.glLineWidth(lineWidth);
		renderer.setColor(color.r, color.g, color.b, color.a);
	}
}
