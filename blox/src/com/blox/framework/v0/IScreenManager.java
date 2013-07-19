package com.blox.framework.v0;


public interface IScreenManager {
	void update();
	void render();
	void setScreen(IScreen screen);
	void setScreenSwitcher(IScreenSwicther switcher);
}