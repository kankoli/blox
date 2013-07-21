package com.blox.test.screen;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.impl.BaseGame;
import com.blox.framework.v0.impl.FadingScreenSwitcher;
import com.blox.framework.v0.util.Game;

public class TurnMazeGame extends BaseGame {
	private IScreen mazeScreen;
	private IScreen splashScreen;
	
	public TurnMazeGame() {
		splashScreen = new SplashScreen(this);
		mazeScreen = new MazeScreen(this);
	}

	@Override
	public void init() {
		Game.world.scale = 1 / 40f;
		
		splashScreen.init();
		mazeScreen.init();

		setScreenSwitcher(new FadingScreenSwitcher(0.33f));
		setScreen(splashScreen);
	}
	
	void showSplashScreen() {
		setScreen(splashScreen);
	}
	
	void showMazeScreen() {
		setScreen(mazeScreen);
	}
}
