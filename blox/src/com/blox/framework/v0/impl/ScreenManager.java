package com.blox.framework.v0.impl;

import java.util.HashMap;
import java.util.Map;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenManager;
import com.blox.framework.v0.IScreenSwicther;
import com.blox.framework.v0.IScreenSwitchListener;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;

public final class ScreenManager implements IScreenManager, IScreenSwitchListener {

	public static final IScreenManager instance = new ScreenManager();

	private Map<String, IScreen> screens;

	private IScreen currentScreen;
	private IScreenSwicther switcher;

	private ScreenManager() {
		screens = new HashMap<String, IScreen>();
	}

	private IScreen getScreen(String screenId) {
		if (screens.containsKey(screenId))
			return screens.get(screenId);

		IScreen screen = Screen.load(screenId);
		screen.init();
		screens.put(screenId, screen);
		return screen;
	}

	@Override
	public void init() {
		initScreenSwitcher();
		switchToDefaultScreen();
	}

	private void initScreenSwitcher() {
		String screenSwitcher = Game.getParam("screen-switcher");
		if (screenSwitcher.startsWith("fading,")) {
			String duration = screenSwitcher.substring(7);
			switcher = new FadingScreenSwitcher(Utils.parseFloat(duration));
		}
		else {
			switcher = new DefaultScreenSwitcher();
		}
		switcher.setListener(this);
	}

	private void switchToDefaultScreen() {
		switchTo(Game.getParam("default-screen"));
	}

	@Override
	public void switchTo(String screenId) {
		IScreen screen = getScreen(screenId);
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