package com.blox.set.view;

import com.blox.framework.v0.util.Game;

public class SplashScreen extends SetGameScreen {
	public SplashScreen(SetGame game) {
		super(game);
	}

	@Override
	public void render() {
		super.render();		
		Game.getTextDrawer().draw("Loading...", 200, 200);
	}

	@Override
	protected void onInit() {
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.showMainMenu();
	}
}
