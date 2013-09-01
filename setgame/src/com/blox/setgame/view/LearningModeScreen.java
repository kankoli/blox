package com.blox.setgame.view;

import com.blox.setgame.controller.LearningModeController;

public class LearningModeScreen extends SetGameScreen {
	private LearningModeController controller;

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
		controller = new LearningModeController(this);
	}

	@Override
	public void update() {
		super.update();
		controller.execute();
	}
}
