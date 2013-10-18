package com.turpgames.framework.v0.impl;

import java.util.HashMap;
import java.util.Map;

import com.turpgames.framework.v0.IView;
import com.turpgames.framework.v0.IViewFinder;
import com.turpgames.framework.v0.IViewSwitcher;
import com.turpgames.framework.v0.util.Game;

public final class ScreenManager implements IViewFinder {

	public static final ScreenManager instance = new ScreenManager();

	private Map<String, Screen> screens;

	private Screen currentScreen;
	private IViewSwitcher switcher;

	private ScreenManager() {
		screens = new HashMap<String, Screen>();
	}

	private void initScreenSwitcher() {
		String screenSwitcher = Game.getParam("screen-switcher");
		switcher = ViewSwitcher.createInstance(screenSwitcher);
		switcher.setViewFinder(this);
	}

	private void switchToDefaultScreen() {
		switchTo(Game.getParam("default-screen"), false);
	}

	public Screen getScreen(String screenId) {
		if (screens.containsKey(screenId))
			return screens.get(screenId);

		Screen screen = Screen.load(screenId);
		screen.init();
		screens.put(screenId, screen);
		return screen;
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}
	
	public void init() {
		initScreenSwitcher();
		switchToDefaultScreen();
	}

	public void update() {
		currentScreen.update();
	}

	public void render() {
		switcher.draw();
	}

	public void switchTo(String screenId, boolean back) {
		boolean screenSwitched = switcher.switchTo(screenId, back);
		if (screenSwitched)
			currentScreen = getScreen(screenId);
	}

	@Override
	public IView findView(String id) {
		return getScreen(id);
	}
}