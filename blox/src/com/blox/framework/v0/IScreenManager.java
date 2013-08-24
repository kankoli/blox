package com.blox.framework.v0;

public interface IScreenManager {
	void init();
	
	void update();

	void render();

	void switchTo(String screenId);
}