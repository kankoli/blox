package com.blox.test.screen;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Game;

class Background extends GameObject {
	Background(String path) {
		addAnimation("bg", path, 1, (int)Game.world.screenWidth, (int)Game.world.screenHeight);
		startAnimation("bg");
		width = Game.world.screenWidth;
		height = Game.world.screenHeight;
	}
}