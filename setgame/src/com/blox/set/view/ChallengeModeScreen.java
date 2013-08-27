package com.blox.set.view;

import com.blox.set.controller.ChallengeModeController;

public class ChallengeModeScreen extends SetGameScreen {
	private ChallengeModeController controller;
	
	@Override
	public void init() {
		super.init();
		controller = new ChallengeModeController(this);
	}
	
	@Override
	public void update() {
		controller.execute();
		super.update();
	}
}
