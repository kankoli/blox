package com.blox.framework.v0.impl;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenSwicther;

public class DefaultScreenSwitcher implements IScreenSwicther {		
	private IScreen screen;

	@Override
	public boolean isSwitching() {
		return false;
	}

	@Override
	public void switchTo(IScreen screen) {
		this.screen = screen;
		this.screen.show();
	}

	@Override
	public void render() {
		screen.render();
	}		
}