package com.blox.set.view;

import com.blox.framework.v0.impl.FormScreen;
import com.blox.set.utils.R;

public class MenuScreen extends FormScreen {
	@Override
	public void init() {
		super.init();
		setForm(R.game.forms.mainMenu, false);
	}
}
