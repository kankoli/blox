package com.blox.test.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MultiScreenGame extends Game {
	SpriteBatch spriteBatch;
	MenuScreen menuScreen;
	GameScreen gameScreen;
	private SwitchScreen switchScreen;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);

		spriteBatch = new SpriteBatch();

		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		switchScreen = new SwitchScreen(menuScreen, 0.5f);
		
		setScreen(menuScreen);
	}

	@Override
	public void setScreen(Screen screen) {
		switchScreen.setScreen((ISwitchableScreen)screen);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		spriteBatch.begin();
		spriteBatch.setColor(1, 1, 1, 1);
		switchScreen.render(Gdx.graphics.getDeltaTime());
		spriteBatch.end();
	}
}
