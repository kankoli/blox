package com.blox.framework.v0.impl;

import com.blox.framework.v0.IGame;
import com.blox.framework.v0.IScreen;

public abstract class BaseGame implements IGame {
	protected void setScreen(IScreen screen) {
		ScreenManager.instance.switchTo(screen.getId());
	}

	@Override
	public void update() {
		ScreenManager.instance.update();
	}

	@Override
	public void render() {
		ScreenManager.instance.render();
	}

	@Override
	public void init() {
		ScreenManager.instance.init();
	}
}