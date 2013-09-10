package com.blox.setgame.view;

import com.blox.setgame.controller.learning.LearningModeController;

public class LearningModeScreen extends SetGameScreen {
	@Override
	public void init() {
		super.init();
		setScreenListener(new LearningModeController(this));
	}
}