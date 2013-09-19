package com.blox.setgame.controller.learning;

import com.blox.setgame.controller.SetGameController;
import com.blox.setgame.model.LearningMode;
import com.blox.setgame.view.LearningModeScreen;

public class LearningModeController extends SetGameController<LearningModeState> implements ILearningModeActionListener {
	final LearningMode model;
	final LearningModeScreen view;

	private LearningModeState tutorialState;
	private LearningModeState waitingState;
	private LearningModeState dealingState;

	public LearningModeController(LearningModeScreen screen) {
		view = screen;
		model = new LearningMode();
		model.setModeListener(this);
		tutorialState = new LearningModeTutorialState(this);
		waitingState = new LearningModeWaitingState(this);
		dealingState = new LearningModeDealingState(this);
	}

	@Override
	public void onScreenActivated() {
		super.onScreenActivated();
		setTutorialState();
		view.registerDrawable(model, 1);
	}

	@Override
	public void onScreenDeactivated() {
		super.onScreenDeactivated();
		model.exitMode();
		view.unregisterDrawable(model);
	}

	@Override
	public void onTutorialEnd() {
		currentState.onTutorialEnd();		
	}
	
	void setTutorialState() {
		setState(tutorialState);
	}
	
	void setDealingState() {
		setState(dealingState);
	}
	
	void setWaitingState() {
		setState(waitingState);
	}
}