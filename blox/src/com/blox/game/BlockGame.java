package com.blox.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blox.framework.v0.impl.CollisionDetector;
import com.blox.framework.v0.impl.libgdx.GdxGameFactory;
import com.blox.framework.v0.util.ToolBox;

public class BlockGame implements ApplicationListener {
	private SpriteBatch batch;

	private Block block;
	private Block block2;

	@Override
	public void create() {
		batch = new SpriteBatch();

		ToolBox.initialize(new GdxGameFactory(batch, null));
		ToolBox.screenWidth = Gdx.graphics.getWidth();
		ToolBox.screenHeight = Gdx.graphics.getHeight();
		//Game.scale = 2 / 48f;

		block = new Block();
		block2 = new Block();
		block2.setLocation(50, 0);
		block2.setStatic(true);
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
		CollisionDetector.detect(block, block2);
		block.draw();
		block2.draw();
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