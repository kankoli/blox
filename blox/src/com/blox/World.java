package com.blox;

import com.badlogic.gdx.math.Vector2;

public final class World {
	
	public static float scale = 1;
	public static float gravity = -9.8f;
	public static float width = 800f;
	public static float height = 480f;
	
	public static Vector2 scale(Vector2 vector) {
		return new Vector2(scale(vector.x), scale(vector.y));
	}

	public static Vector2 descale(Vector2 vector) {
		return new Vector2(descale(vector.x), descale(vector.y));
	}

	public static float scale(float f) {
		return f * scale;
	}

	public static float descale(float f) {
		return f / scale;
	}
}
