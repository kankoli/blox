package com.turpgames.framework.v0.impl.libgdx;

import java.io.InputStream;

import org.w3c.dom.Document;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.turpgames.framework.v0.IGame;
import com.turpgames.framework.v0.metadata.GameMetadata;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public class GdxGame implements ApplicationListener {
	static SpriteBatch spriteBatch;
	static ShapeRenderer shapeRenderer;

	private IGame game;
	private volatile boolean paused;

	public GdxGame() {
		
	}

	private void initGdx() {
		Gdx.input.setCatchBackKey(true);
		Texture.setEnforcePotImages(false);
	}

	private static Document getGameXml() {
		InputStream is = Gdx.files.internal("game.xml").read();
		return Utils.loadXml(is);
	}

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		initGdx();

		Game.initialize(getGameXml());
		Game.getInputManager().activate();

		game = (IGame) Utils.createInstance(GameMetadata.getGameClass());
		game.init();
	}

	@Override
	public void render() {
		if (paused)
			return;
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		game.update();

		spriteBatch.begin();
		game.draw();
		spriteBatch.end();
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
		spriteBatch.dispose();
		Game.dispose();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}
}
