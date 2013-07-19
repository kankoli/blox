package com.blox.framework.v0.impl;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenManager;
import com.blox.framework.v0.IScreenSwicther;
import com.blox.framework.v0.IScreenSwitchListener;

public class ScreenManager implements IScreenManager, IScreenSwitchListener {
	private IScreen currentScreen;	
	private IScreenSwicther switcher; 
	
	public ScreenManager() {
		switcher = new DefaultScreenSwitcher();
	}
	
	public ScreenManager(IScreenSwicther switcher) {
		setScreenSwitcher(switcher);
	}
	
	@Override
	public void setScreenSwitcher(IScreenSwicther switcher) {
		this.switcher = switcher;
		this.switcher.setListener(this);
	}
	
	@Override
	public void setScreen(IScreen screen) {
		switcher.switchTo(screen);
		currentScreen = screen;
	}

	@Override
	public void update() {
		if (!switcher.isSwitching())
			currentScreen.update();
	}

	@Override
	public void render() {
		if (switcher.isSwitching())
			switcher.render();
		else
			currentScreen.render();
	}

	@Override
	public void switchEnd(IScreen oldScreen, IScreen newScreen) {
		if (oldScreen != null)
			oldScreen.deactivated();
		newScreen.activated();
	}
}