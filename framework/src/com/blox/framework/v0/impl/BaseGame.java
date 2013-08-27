package com.blox.framework.v0.impl;

import com.blox.framework.v0.IGame;

public abstract class BaseGame implements IGame {
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