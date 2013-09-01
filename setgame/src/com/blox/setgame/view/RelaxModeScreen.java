package com.blox.setgame.view;

import com.blox.setgame.controller.RelaxModeController;

public class RelaxModeScreen extends SetGameScreen {
	private RelaxModeController controller;

	@Override
	public void init() {
		super.init();
		controller = new RelaxModeController(this);
	}

	@Override
	public void update() {
		super.update();
		controller.execute();
	}
}
