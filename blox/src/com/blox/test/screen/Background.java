package com.blox.test.screen;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Game;

class Background extends GameObject {
	Background(String path) {
		width = Game.world.screenWidth;
		height = Game.world.screenHeight;
		addAnimation("bg", path, 1, (int)width, (int)height);
		startAnimation("bg");
	}
}