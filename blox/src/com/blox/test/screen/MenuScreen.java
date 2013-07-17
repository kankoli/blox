package com.blox.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MenuScreen implements Screen {
	MultiScreenGame game;
	
	MenuScreen(MultiScreenGame game) {
		this.game = game;
	}

	@Override
	public void render(float dt) {
		if (Gdx.input.justTouched())
			game.setScreen(game.gameScreen);
	}

	@Override
	public void show() {
		System.out.println("menu show");
	}

	@Override
	public void hide() {
		System.out.println("menu hide");
	}

	@Override
	public void dispose() {
		System.out.println("menu dispose");
	}

	@Override
	public void resume() {
		System.out.println("menu resume");
	}

	@Override
	public void resize(int arg0, int arg1) {
		System.out.println("menu resize");
	}

	@Override
	public void pause() {
		System.out.println("menu pause");
	}
}
