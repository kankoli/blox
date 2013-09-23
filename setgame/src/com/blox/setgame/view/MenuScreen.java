package com.blox.setgame.view;

import com.blox.framework.v0.impl.FormScreen;
import com.blox.setgame.model.Logo;
import com.blox.setgame.utils.R;

public class MenuScreen extends FormScreen {
	@Override
	public void init() {
		super.init();

		Logo logo = new Logo();
		logo.getColor().a = 0.25f;
		registerDrawable(logo, 1);

		setForm(R.game.forms.mainMenu, false);
	}
}