package com.blox.setgame.controller.learning;

import com.blox.setgame.controller.SetGameController;
import com.blox.setgame.model.LearningMode;
import com.blox.setgame.view.LearningModeScreen;

public class LearningModeController extends SetGameController<LearningModeState> {
	final LearningMode model;
	final LearningModeScreen view;

	private LearningModeState waitingState;
	private LearningModeState dealingState;

	public LearningModeController(LearningModeScreen screen) {
		view = screen;
		model = new LearningMode();
		model.setGameListener(this);
		waitingState = new LearningModeWaitingState(this);
		dealingState = new LearningModeDealingState(this);
	}

	@Override
	public void onScreenActivated() {
		super.onScreenActivated();
		setDealingState();
		view.registerDrawable(model, 1);
	}

	@Override
	public void onScreenDeactivated() {
		super.onScreenDeactivated();
		model.exitMode();
		view.unregisterDrawable(model);
	}
	
	void setDealingState() {
		setState(dealingState);
	}
	
	void setWaitingState() {
		setState(waitingState);
	}
}