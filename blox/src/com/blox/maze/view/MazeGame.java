package com.blox.maze.view;

import com.blox.framework.v0.impl.BaseGame;
import com.blox.framework.v0.impl.FadingScreenSwitcher;

public class MazeGame extends BaseGame {
	public static final float virtualWidth = 550;
	public static final float virtualHeight = 800;

	private SplashScreen splashScreen;
	private MainMenuScreen mainMenuScreen;
	private MazeScreen mazeScreen;

	public MazeGame() {
	}

	@Override
	public void init() {
		splashScreen = new SplashScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		mazeScreen = new MazeScreen(this);

		splashScreen.init();
		mainMenuScreen.init();
		mazeScreen.init();

		setScreenSwitcher(new FadingScreenSwitcher(0.5f));
		setScreen(splashScreen);
	}
	
	void showSplash() {
		setScreen(splashScreen);
	}
	
	void showMainMenu() {
		setScreen(mainMenuScreen);
	}
	
	void showMaze() {
		setScreen(mazeScreen);
	}

	@Override
	public float getVirtualWidth() {
		return virtualWidth;
	}
	
	@Override
	public float getVirtualHeight() {
		return virtualHeight;
	}
}