package com.blox.framework.v0.impl;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenSwicther;
import com.blox.framework.v0.IScreenSwitchListener;

public class DefaultScreenSwitcher implements IScreenSwicther {
	private IScreen screen;
	private IScreenSwitchListener listener;

	@Override
	public boolean isSwitching() {
		return false;
	}

	@Override
	public void switchTo(IScreen screen) {
		IScreen oldScreen = this.screen;
		this.screen = screen;
		if (listener != null)
			listener.switchEnd(oldScreen, this.screen);
	}

	@Override
	public void render() {
		screen.render();
	}

	@Override
	public void setListener(IScreenSwitchListener listener) {
		this.listener = listener;
	}
}