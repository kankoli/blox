package com.blox.framework.v0.util;

public class GameWorld {
	public float screenWidth = 480;
	public float screenHeight = 800;
	public float scale = 1.0f;
	public Vector gravity;
	
	GameWorld() {
		gravity = new Vector();
	}	

	public float scale(float f) {
		return f * scale;
	}

	public float descale(float f) {
		return f / scale;
	}
}
