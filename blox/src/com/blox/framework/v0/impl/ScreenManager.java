package com.blox.framework.v0.impl;

import java.util.HashMap;
import java.util.Map;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenManager;
import com.blox.framework.v0.IView;
import com.blox.framework.v0.IViewFinder;
import com.blox.framework.v0.IViewSwitcher;
import com.blox.framework.v0.util.Game;

public final class ScreenManager implements IScreenManager, IViewFinder {

	public static final IScreenManager instance = new ScreenManager();

	private Map<String, IScreen> screens;

	private IScreen currentScreen;
	private IViewSwitcher switcher;

	private ScreenManager() {
		screens = new HashMap<String, IScreen>();
	}

	private void initScreenSwitcher() {
		String screenSwitcher = Game.getParam("screen-switcher");
		switcher = ViewSwitcher.createInstance(screenSwitcher);
		switcher.setViewFinder(this);
	}

	private void switchToDefaultScreen() {
		switchTo(Game.getParam("default-screen"));
	}

	@Override
	public IScreen getScreen(String screenId) {
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

	@Override
	public void switchTo(String screenId) {
		switcher.switchTo(screenId);
		currentScreen = getScreen(screenId);
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
	public IView findView(String id) {
		return getScreen(id);
	}
}