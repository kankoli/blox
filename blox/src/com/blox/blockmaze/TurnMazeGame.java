package com.blox.blockmaze;

import com.badlogic.gdx.ApplicationListener;
import com.blox.framework.v0.IGame;
import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.impl.libgdx.GdxGame;
import com.blox.framework.v0.impl.libgdx.ILibGdxGame;

public class TurnMazeGame implements IGame, ILibGdxGame {
	private final int mazeWidth = 12;
	private final int mazeHeight = 12;
	
	private IScreen mazeScreen;
	private ApplicationListener gdxApp;

	public TurnMazeGame() {
		mazeScreen = new TurnMaze(mazeWidth, mazeHeight);
		gdxApp = GdxGame.create(this);
	}

	@Override
	public void init() {
		mazeScreen.init();
	}

	@Override
	public void render() {
		mazeScreen.render();
	}

	@Override
	public void update() {
		mazeScreen.update();		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public ApplicationListener getApplicationListener() {
		return gdxApp;
	}
}
