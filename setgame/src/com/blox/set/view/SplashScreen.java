package com.blox.set.view;

import com.blox.framework.v0.impl.ScreenManager;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.set.utils.R;
import com.blox.set.utils.SetFonts;

public class SplashScreen extends SetGameScreen {
	private boolean loading = true;
	@Override
	public void render() {
		super.render();
		if (loading)
			TextDrawer.draw(SetFonts.font72, "Loading...", TextDrawer.AlignCentered);
		else
			ScreenManager.instance.switchTo(R.game.screens.menu, false);
	}
	
	@Override
	public void init() {
		super.init();
		SetFonts.init();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				loading = false;
			}
		}).start();
	}
}
