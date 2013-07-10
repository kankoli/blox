package com.blox.test;

import com.badlogic.gdx.math.Vector2;

public class Ball {
	public Vector2 position;
	public float radius;

	public Vector2 a;
	public Vector2 v;

	public Ball(float x, float y, float r) {
		position = new Vector2(x, y);
		radius = r;
		a = new Vector2();
		v = new Vector2();
	}

	public void move(float dt, float friction) {
		v.mul(1 - dt * friction);

		float dx = (v.x * dt + 0.5f * a.x * dt * dt);
		float dy = (v.y * dt + 0.5f * a.y * dt * dt);

		position.x += dx;
		position.y += dy;

		v.add(a.tmp().mul(dt));
	}

	public String toString() {
		return "x: " + (int) position.x + "\ny: " + (int) position.y + "\nvx: "
				+ (int) v.x + "\nvy: " + (int) v.y + "\nax: " + (int) a.x
				+ "\nay: " + (int) a.y;
	}
}
