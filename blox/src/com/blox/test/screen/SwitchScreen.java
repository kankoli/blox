package com.blox.test.screen;

import com.badlogic.gdx.Screen;

public class SwitchScreen implements Screen {
	private ISwitchableScreen currentScreen;
	private ISwitchableScreen oldScreen;
	private float duration;
	private float elapsed;

	public SwitchScreen(ISwitchableScreen defaultScreen, float duration) {
		this.currentScreen = defaultScreen;
		this.duration = duration;
		this.elapsed = duration;
	}

	void setScreen(ISwitchableScreen newScreen) {
		if (currentScreen != null && currentScreen != newScreen) {
			elapsed = 0;
			oldScreen = currentScreen;
		}
		currentScreen = newScreen;
	}

	@Override
	public void render(float delta) {
		elapsed += delta;
		if (elapsed >= duration) {
			currentScreen.render(delta);
			return;
		}

		float alpha = elapsed / duration;
		oldScreen.drawSwitch(1 - alpha);
		currentScreen.drawSwitch(alpha);
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

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

	@Override
	public void dispose() {

	}
}
