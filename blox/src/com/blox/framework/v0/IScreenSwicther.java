package com.blox.framework.v0;


public interface IScreenSwicther {
	boolean isSwitching();
	void switchTo(IScreen screen);
	void render();
}