package com.blox.maze;

import com.blox.framework.v0.impl.GameObject;

public class Background extends GameObject {
	public Background() {
		addAnimation("bg", "screen2.jpg", 1, 480, 800);
		startAnimation("bg");
		width = 480;
		height = 800;
	}
}
