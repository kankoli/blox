package com.blox.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blox.framework.v0.libgdx.GdxGameFactory;
import com.blox.framework.v0.util.Game;

public class BlockGame implements ApplicationListener {
	private SpriteBatch batch;

	private Block block;

	@Override
	public void create() {
		batch = new SpriteBatch();

		Game.initialize(new GdxGameFactory(batch, null));
		Game.width = Gdx.graphics.getWidth();
		Game.height = Gdx.graphics.getHeight();

		block = new Block();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		block.draw();
		batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}