package com.blox.set.view;

import com.blox.framework.v0.impl.ScreenManager;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.set.utils.R;

public class SplashScreen extends SetGameScreen {
	@Override
	public void update() {
		if (!Game.getResourceManager().isLoading())
			ScreenManager.instance.switchTo(R.game.screens.menu, false);
	}

	@Override
	public void render() {
		super.render();
		TextDrawer.draw(FontManager.defaultFont, "Loading: " + Game.getResourceManager().getLoadingPercent() + "%", TextDrawer.AlignCentered);
	}
}