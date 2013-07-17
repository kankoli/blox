package com.blox.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements ISwitchableScreen {

	MultiScreenGame game;

	private final int screenWidth = 480;
	private final int screenHeight = 800;

	private final int width = 20;
	private final int height = 20;

	private Texture texture;

	float vx = 90;
	float vy = 80;
	float px = 100;
	float py = 100;

	Texture bg;
	GameScreen(MultiScreenGame game) {
		this.game = game;
		this.texture = new Texture(Gdx.files.internal("turnmaze/body_full.png"));
		this.bg = new Texture(Gdx.files.internal("screen2.jpg"));
	}

	@Override
	public void render(float dt) {
		game.spriteBatch.draw(bg, 0, 0);
		if (Gdx.input.justTouched()) {
			game.setScreen(game.menuScreen);
			return;
		}

		px += vx * dt;
		py += vy * dt;

		if (px <= 0 || px >= screenWidth - width) {
			vx = -vx;
		}
		if (py <= 0 || py >= screenHeight - height) {
			vy = -vy;
		}

		game.spriteBatch.draw(texture, px, py);
	}

	@Override
	public void drawSwitch(float alpha) {
		game.spriteBatch.setColor(1, 1, 1, alpha);
		game.spriteBatch.draw(bg, 0, 0);
		game.spriteBatch.draw(texture, px, py);
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
