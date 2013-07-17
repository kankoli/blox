package com.blox.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen implements ISwitchableScreen {
	MultiScreenGame game;
	Texture bg;

	MenuScreen(MultiScreenGame game) {
		this.game = game;
		this.bg = new Texture(Gdx.files.internal("screen1.jpg"));
	}

	@Override
	public void render(float dt) {
		game.spriteBatch.draw(bg, 0, 0);
		if (Gdx.input.justTouched())
			game.setScreen(game.gameScreen);
	}

	@Override
	public void drawSwitch(float alpha) {
		game.spriteBatch.setColor(1, 1, 1, alpha);
		render(0);
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void pause() {

	}
}
