package com.blox.framework.v0;

public interface IScreen {
	String getId();
	
	void init();

	void activated();

	void update();

	void render();

	void deactivated();
}
