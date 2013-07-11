package com.blox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class ScaledShapeRenderer {
	private ShapeRenderer renderer;
	private Color color;

	public ScaledShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}

	public void setColor(Color color) {
		renderer.setColor(color);
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public void line(Vector2 start, Vector2 end) {
		Vector2 s = World.descale(start);
		Vector2 e = World.descale(end);
		renderer.begin(ShapeType.Line);
		renderer.line(s.x, s.y, e.x, e.y);
		renderer.end();
	}

	public void line(float p1x, float p1y, float p2x, float p2y) {
		renderer.begin(ShapeType.Line);
		renderer.line(World.descale(p1x), World.descale(p1y),
				World.descale(p2x), World.descale(p2y));
		renderer.end();
	}
	
	public void rect(float x, float y, float w, float h, Color color) {
		renderer.begin(ShapeType.FilledRectangle);
		renderer.filledRect(World.descale(x), World.descale(y), World.descale(w),
				World.descale(h), color, color, color, color);
		renderer.end();
	}
	
	public void rect(float x, float y, float w, float h, Color color1, Color color2, Color color3, Color color4) {
		renderer.begin(ShapeType.FilledRectangle);
		renderer.filledRect(World.descale(x), World.descale(y), World.descale(w),
				World.descale(h), color1, color2, color3, color4);
		renderer.end();
	}
	
	public void rect(float x, float y, float w, float h) {
		renderer.begin(ShapeType.Rectangle);
		renderer.rect(World.descale(x), World.descale(y), World.descale(w),
				World.descale(h));
		renderer.end();
	}

	public void circle(Vector2 position, float radius) {
		Vector2 p = World.descale(position);
		float r = World.descale(radius);
		renderer.begin(ShapeType.Circle);
		renderer.circle(p.x, p.y, r);
		renderer.end();
	}

	public void translate(float x, float y) {
		renderer.translate(x, y, 0);
	}
	
	public void rotate(float theta) {
		renderer.rotate(0, 0, 1, theta);
	}
	
	public void dispose() {
		renderer.dispose();
	}
}
