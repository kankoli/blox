package com.blox.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.blox.BloxRectangle;
import com.blox.ScaledShapeRenderer;
import com.blox.World;

public class MazePlayer {
	private BloxRectangle rect;

	private Vector2 p;
	private Vector2 v;
	private Vector2 a;

	MazePlayer() {
		p = new Vector2();
		v = new Vector2();
		a = new Vector2();
		rect = new BloxRectangle(0, 0, 0.9f, 0.9f);
	}

	public float getHeight() {
		return 0.9f;
	}

	public float getWidth() {
		return 0.9f;
	}

	public float getX() {
		return p.x;
	}

	public float getY() {
		return p.y;
	}

	public void setLocation(float x, float y) {
		p.x = x;
		p.y = y;
	}

	public void move() {
		float dt = Gdx.graphics.getDeltaTime();

		float dx = v.x * dt + 0.5f * a.x * dt * dt;
		float dy = v.y * dt + 0.5f * a.y * dt * dt;

		v.x += a.x * dt;
		v.y += a.y * dt;

		p.x += dx;
		p.y += dy;
	}

	public void draw(ScaledShapeRenderer renderer, float theta, float ox,
			float oy, float tx, float ty) {
		rect.setLocation(p.x + tx + 0.05f, p.y + ty + 0.05f);
		rect.setRotatationOriginX(tx + ox);
		rect.setRotatationOriginY(ty + oy);
		rect.setRotation(theta);

		Color c = renderer.getColor();
		renderer.setColor(Color.RED);
		rect.draw(renderer);
		renderer.setColor(c);
	}

	public void beginMove(float theta) {
		int orientation = (int) Math.round(Math.toDegrees(theta)) % 360;
		if (orientation < 0)
			orientation += 360;

		if (orientation == 0) {
			a.x = 0;
			a.y = World.gravity;
		} else if (orientation == 90) {
			a.y = 0;
			a.x = World.gravity;
		} else if (orientation == 180) {
			a.x = 0;
			a.y = -World.gravity;
		} else {
			a.y = 0;
			a.x = -World.gravity;
		}
	}

	public void stop() {
		v.x = 0;
		v.y = 0;
		a.x = 0;
		a.y = 0;
	}
}
