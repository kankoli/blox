package com.blox.test.screen;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.Game;

class SplashScreen extends Screen {
	private TurnMazeGame game;

	SplashScreen(TurnMazeGame game) {
		this.game = game;
	}

	@Override
	public void init() {
		super.init();
		super.registerInputListener(this);
		super.registerDrawable(new Background("screen1.jpg"), 1);
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		game.showMazeScreen();
		return super.touchUp(x, y, pointer, button);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
			Game.exit();
			return true;
		}
		return super.keyDown(keycode);
	}
}
