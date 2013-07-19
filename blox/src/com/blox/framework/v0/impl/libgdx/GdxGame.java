package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blox.framework.v0.IGame;
import com.blox.framework.v0.util.Game;

public class GdxGame implements ApplicationListener {
	static SpriteBatch spriteBatch;

	private IGame game;

	public GdxGame(IGame game) {
		this.game = game;
	}
	
	private void initGdx() {
		Gdx.input.setCatchBackKey(true);
		Texture.setEnforcePotImages(false);
		Game.initialize(new GdxGameProvider());
		Game.world.screenWidth = Gdx.graphics.getWidth();
		Game.world.screenHeight = Gdx.graphics.getHeight();
	}

	@Override
	public void create() {
		initGdx();
		
		spriteBatch = new SpriteBatch();
		
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
		Game.getDisposeManager().disposeAll();
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
