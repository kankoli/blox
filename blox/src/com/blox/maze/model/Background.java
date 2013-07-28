package com.blox.maze.model;

public class Background extends MazeGameObject {
	public Background() {
		addAnimation("bg", "screen2.jpg", 1, 480, 800);
		startAnimation("bg");
		width = 480;
		height = 800;
	}
}
