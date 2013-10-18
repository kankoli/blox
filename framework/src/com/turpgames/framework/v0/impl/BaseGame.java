package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.IGame;
import com.turpgames.framework.v0.effects.EffectManager;
import com.turpgames.framework.v0.util.TimerManager;

public abstract class BaseGame implements IGame {
	@Override
	public void update() {
		TimerManager.instance.execute();
		EffectManager.instance.execute();
		ScreenManager.instance.update();
	}

	@Override
	public void draw() {
		ScreenManager.instance.render();
	}

	@Override
	public void init() {
		ScreenManager.instance.init();
	}
}