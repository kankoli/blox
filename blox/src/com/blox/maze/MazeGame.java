package com.blox.maze;

import com.badlogic.gdx.ApplicationListener;
import com.blox.framework.v0.IGame;
import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.impl.libgdx.GdxGame;
import com.blox.framework.v0.impl.libgdx.ILibGdxGame;

public class MazeGame implements IGame, ILibGdxGame {	
	private IScreen mazeScreen;
	private ApplicationListener gdxApp;

	public MazeGame() {
		gdxApp = GdxGame.create(this);
		mazeScreen = new MazeScreen();
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
