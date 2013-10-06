package com.blox.setgame.controller.learning;

import com.blox.setgame.model.ICardDealerListener;

public class LearningModeDealingState extends LearningModeState implements ICardDealerListener {
	public LearningModeDealingState(LearningModeController controller) {
		super(controller);
		model.setDealerListener(this);
	}

	@Override
	protected void activated() {
		model.deal();
	}

	@Override
	public void onDealEnd() {
		controller.setWaitingState();
	}
}
