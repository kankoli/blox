package com.blox.setgame.controller.practice;

import com.blox.setgame.model.ICardDealerListener;

public class PracticeModeDealingState extends PracticeModeState implements ICardDealerListener {
	public PracticeModeDealingState(PracticeModeController controller) {
		super(controller);
		model.setDealerListener(this);
	}

	@Override
	protected void activated() {
		model.deal();
	}

	@Override
	public void onDealEnd() {
		model.dealEnd();
		controller.setWaitingState();
	}
}