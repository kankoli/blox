package com.blox.setgame.view;

import com.blox.setgame.controller.relax.RelaxModeController;

public class RelaxModeScreen extends SetGameScreen {
	@Override
	public void init() {
		super.init();
		setScreenListener(new RelaxModeController(this));
	}
}