package com.blox.set.view;

import com.blox.framework.v0.util.TextDrawer;
import com.blox.set.utils.SetFonts;

public class SplashScreen extends SetGameScreen {
	@Override
	public void render() {
		super.render();
		TextDrawer.draw(SetFonts.font72, "Loading...", TextDrawer.AlignCentered);
	}
}
