package com.blox.framework.v0.util;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenSwicther;
import com.blox.framework.v0.impl.DefaultScreenSwitcher;

public class ScreenManager {
	private IScreen currentScreen;	
	private IScreenSwicther switcher; 
	
	public ScreenManager() {
		switcher = new DefaultScreenSwitcher();
	}
	
	public ScreenManager(IScreenSwicther switcher) {
		setScreenSwitcher(switcher);
	}
	
	public void setScreenSwitcher(IScreenSwicther switcher) {
		this.switcher = switcher;
	}
	
	public void setScreen(IScreen screen) {
		switcher.switchTo(screen);
		currentScreen = screen;
	}

	public void update() {
		if (!switcher.isSwitching())
			currentScreen.update();
	}

	public void render() {
		if (switcher.isSwitching()) {
			switcher.render();
		}
		else {
			currentScreen.render();
		}
	}
}