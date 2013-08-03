package com.blox.maze.view;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.impl.BaseGame;

public class MazeGame extends BaseGame {
	public static final float virtualWidth = 550;
	public static final float virtualHeight = 800;
	
	private IScreen mazeScreen;

	public MazeGame() {
		mazeScreen = new MazeScreen(this);
	}

	@Override
	public void init() {
		mazeScreen.init();

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