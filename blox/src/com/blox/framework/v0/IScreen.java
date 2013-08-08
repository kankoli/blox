package com.blox.framework.v0;

public interface IScreen extends IUpdatable {
	void init();

	void activated();

	void render();

	void deactivated();
}
