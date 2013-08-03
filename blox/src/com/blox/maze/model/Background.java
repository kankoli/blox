package com.blox.maze.model;

import com.blox.framework.v0.util.Game;

public class Background extends MazeGameObject {
	public Background() {
		addAnimation("bg", "screen2.jpg", 1, 480, 800);
		startAnimation("bg");
		width = Game.getScreenWidth();
		height = Game.getScreenHeight();
	}
	
	@Override
	public boolean ignoreViewportOffset() {
		return true;
	}
	
	@Override
	public boolean ignoreViewportScaling() {
		return true;
	}
}
