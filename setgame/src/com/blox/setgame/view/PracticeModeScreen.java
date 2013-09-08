package com.blox.setgame.view;

import com.blox.setgame.controller.practice.PracticeModeController;

public class PracticeModeScreen extends SetGameScreen {
	private PracticeModeController controller;

	@Override
	public void activated() {
		super.activated();
		controller.activated();
	}

	@Override
	public void deactivated() {
		super.deactivated();
		controller.deactivated();
	}

	@Override
	public void init() {
		super.init();
		controller = new PracticeModeController(this);
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return controller.screenTapped();
	}
}
