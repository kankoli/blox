package com.blox.set.view;

import com.blox.framework.v0.IResourceInitListener;
import com.blox.framework.v0.util.Game;

public class SplashScreen extends SetGameScreen implements IResourceInitListener {
	private boolean loading = true;
	@Override
	public void render() {
		super.render();
//		if (loading)
//			//TextDrawer.draw(FontManager.defaultFont, "Loading...", TextDrawer.AlignCentered);
//			System.out.println("loading...");
//		else
//			//ScreenManager.instance.switchTo(R.game.screens.menu, false);
//			System.out.println("load OK!!!");
	}
	
	@Override
	public void init() {
		super.init();
		Game.getResourceManager().init(this);
	}

	@Override
	public void resourcesInited() {
		System.out.println("loaded!");
		loading = false;
	}
}