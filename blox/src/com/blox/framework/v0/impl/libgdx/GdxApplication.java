package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blox.framework.v0.IGame;
import com.blox.framework.v0.util.ToolBox;

class GdxApplication implements ApplicationListener {	
	private IGame game;

	private SpriteBatch spriteBatch;
	
	GdxApplication(IGame game) {
		this.game = game;
	}

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		
		ToolBox.initialize(new GdxGameFactory(spriteBatch));
		
		game.init();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		game.update();
		
		spriteBatch.begin();
		game.render();
		spriteBatch.end();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
}
