package com.blox.setgame.view;

import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.ScreenManager;
import com.blox.setgame.model.Logo;
import com.blox.setgame.utils.R;

public abstract class SetGameScreen extends Screen {	
	@Override
	public void init() {
		super.init();

		Logo logo = new Logo();
		logo.getColor().a = 0.25f;
		registerDrawable(logo, 1);
		
		registerInputListener(this);
	}

	@Override
	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.game.screens.menu, true);
		return true;
	}
}