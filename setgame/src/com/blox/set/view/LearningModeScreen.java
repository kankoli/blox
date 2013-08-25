package com.blox.set.view;

import com.blox.set.controller.LearningModeController;

public class LearningModeScreen extends SetGameScreen {
	private LearningModeController controller;
	
	@Override
	public void init() {
		super.init();
		controller = new LearningModeController(this);
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
