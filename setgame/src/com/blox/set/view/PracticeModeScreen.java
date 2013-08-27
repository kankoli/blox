package com.blox.set.view;

import com.blox.set.controller.PracticeModeController;

public class PracticeModeScreen extends SetGameScreen {
	private PracticeModeController controller;

	@Override
	public void init() {
		super.init();
		controller = new PracticeModeController(this);
	}

	@Override
	public void update() {
		controller.execute();
		super.update();
	}
}
