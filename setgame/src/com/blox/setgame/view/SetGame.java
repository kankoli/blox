package com.blox.setgame.view;

import com.blox.framework.v0.impl.BaseGame;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.ScreenManager;
import com.blox.setgame.model.Toolbar;

public class SetGame extends BaseGame {
	private static Toolbar toolbar;

	@Override
	public void init() {
		super.init();
	}

	static void activateToolbar() {
		Toolbar.init();
		toolbar = Toolbar.getInstance();
		toolbar.setListener(new Toolbar.IToolbarListener() {
			@Override
			public void onToolbarBack() {
				Screen screen = ScreenManager.instance.getCurrentScreen();
				if (screen instanceof SetGameScreen)
					((SetGameScreen) screen).back();
			}
		});
	}

	@Override
	public void render() {
		super.render();
		if (toolbar != null)
			toolbar.draw();
	}
}