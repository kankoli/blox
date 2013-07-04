package com.blox;

import com.badlogic.gdx.math.Vector2;

public class WorldScaler {
	private float scale;

	public WorldScaler(float scale) {
		this.scale = scale;
	}

	public Vector2 scale(Vector2 vector) {
		return new Vector2(scale(vector.x), scale(vector.y));
	}

	public Vector2 descale(Vector2 vector) {
		return new Vector2(descale(vector.x), descale(vector.y));
	}

	public float scale(float f) {
		return f * scale;
	}

	public float descale(float f) {
		return f / scale;
	}
}
