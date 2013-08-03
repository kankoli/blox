package com.blox.maze.model;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Game;

public class Background extends GameObject {
	public Background() {
		addAnimation("bg", "screen2.jpg", 1, 480, 800);
		startAnimation("bg");
		width = Game.world.getScreenWidth();
		height = Game.world.getScreenHeight();
	}
}
