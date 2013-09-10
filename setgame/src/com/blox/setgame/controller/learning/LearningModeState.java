package com.blox.setgame.controller.learning;

import com.blox.setgame.controller.SetGameState;
import com.blox.setgame.model.LearningMode;
import com.blox.setgame.view.LearningModeScreen;

public abstract class LearningModeState extends SetGameState {
	final LearningMode model;
	final LearningModeScreen view;
	final LearningModeController controller;

	public LearningModeState(LearningModeController controller) {
		this.controller = controller;
		this.model = controller.model;
		this.view = controller.view;
	}

	@Override
	public void onSetFound() {

	}

	@Override
	public void onInvalidSetSelected() {

	}
}