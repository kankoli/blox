package com.blox.setgame.view;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.ScreenManager;

public abstract class SetGameScreen extends Screen {
	@Override
	public void init() {
		super.init();
		registerInputListener(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
			ScreenManager.instance.switchTo("menu", true);
		return super.keyDown(keycode);
	}
}