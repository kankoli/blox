package com.blox.setgame.view;

import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.ScreenManager;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.setgame.utils.R;

public class SplashScreen extends Screen {
	@Override
	public void render() {
		super.render();
		TextDrawer.draw(FontManager.defaultFont, "Loading: " + Game.getResourceManager().getLoadingPercent() + "%");
	}

	@Override
	public void update() {
		if (!Game.getResourceManager().isLoading())
			ScreenManager.instance.switchTo(R.game.screens.menu, false);
	}
}