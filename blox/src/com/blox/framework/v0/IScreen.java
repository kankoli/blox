package com.blox.framework.v0;

public interface IScreen {
	void init();

	void activated();

	void update();

	void render();

	void deactivated();
}
