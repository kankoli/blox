package com.blox.setgame.view;

import com.blox.setgame.controller.ChallengeModeController;

public class ChallengeModeScreen extends SetGameScreen {
	private ChallengeModeController controller;

	@Override
	public void init() {
		super.init();
		controller = new ChallengeModeController(this);
	}

	@Override
	public void update() {
		super.update();
		controller.execute();
	}
}
