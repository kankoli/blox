package com.blox;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class ScaledShapeRenderer {
	private ShapeRenderer renderer;

	public ScaledShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void setColor(int rgb) {
		setColor(rgb, rgb, rgb);
	}

	public void setColor(int r, int g, int b) {
		setColor(r, g, b, 1);
	}

	public void setColor(int r, int g, int b, int a) {
		renderer.setColor(r / 255f, g / 255f, b / 255f, a / 255f);
	}

	public void line(Vector2 start, Vector2 end) {
		Vector2 s = World.descale(start);
		Vector2 e = World.descale(end);
		renderer.begin(ShapeType.Line);
		renderer.line(s.x, s.y, e.x, e.y);
		renderer.end();
	}

	public void circle(Vector2 position, float radius) {
		Vector2 p = World.descale(position);
		float r = World.descale(radius);
		renderer.begin(ShapeType.Circle);
		renderer.circle(p.x, p.y, r);
		renderer.end();
	}

	public void dispose() {
		renderer.dispose();
	}
}