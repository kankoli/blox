package com.blox.framework.v0;

public interface IGame {
	IScreen getScreen();

	void pause();

	void render();

	void resize(int width, int height);

	void resume();

	void setScreen(IScreen screen);
}
