package com.blox.framework.v0;

public interface IScreen {
	void hide();

	void pause();

	void render(float delta);

	void resize(int width, int height);

	void resume();

	void show();
}
