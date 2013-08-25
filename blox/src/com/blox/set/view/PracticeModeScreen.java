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
	public void render() {
		draw();
	}

	@Override
	public void update() {
		controller.work();
		super.update();
	}
}
