package com.blox.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {

	MultiScreenGame game;

	private final int screenWidth = 480;
	private final int screenHeight = 800;

	private final int width = 20;
	private final int height = 20;

	private Texture texture;

	float vx = 190;
	float vy = 180;
	float px = 100;
	float py = 100;

	GameScreen(MultiScreenGame game) {
		this.game = game;
		this.texture = new Texture(Gdx.files.internal("turnmaze/body_full.png"));
	}

	@Override
	public void render(float dt) {
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
	public void show() {
		System.out.println("game show");
	}

	@Override
	public void hide() {
		System.out.println("game hide");
	}

	@Override
	public void dispose() {
		System.out.println("game dispose");
	}

	@Override
	public void resume() {
		System.out.println("game resume");
	}

	@Override
	public void resize(int arg0, int arg1) {
		System.out.println("game resize");
	}

	@Override
	public void pause() {
		System.out.println("game pause");
	}
}
