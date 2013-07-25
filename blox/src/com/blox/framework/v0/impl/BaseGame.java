package com.blox.framework.v0.impl;

import com.blox.framework.v0.IGame;
import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenManager;
import com.blox.framework.v0.IScreenSwicther;

public abstract class BaseGame implements IGame {
	private IScreenManager screenManager;

	protected BaseGame() {
		screenManager = new ScreenManager();
	}

	protected void setScreen(IScreen screen) {
		screenManager.setScreen(screen);
	}

	protected void setScreenSwitcher(IScreenSwicther switcher) {
		screenManager.setScreenSwitcher(switcher);
	}

	@Override
	public void update() {
		screenManager.update();
	}

	@Override
	public void render() {
		screenManager.render();
	}

	@Override
	public void init() {

	}
}