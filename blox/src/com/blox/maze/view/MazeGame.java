package com.blox.maze.view;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.impl.BaseGame;

public class MazeGame extends BaseGame {
	private IScreen mazeScreen;

	public MazeGame() {
		mazeScreen = new MazeScreen(this);
	}

	@Override
	public void init() {
		mazeScreen.init();

		setScreen(mazeScreen);
	}
}