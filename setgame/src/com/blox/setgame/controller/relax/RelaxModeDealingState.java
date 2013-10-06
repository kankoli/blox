package com.blox.setgame.controller.relax;

import com.blox.setgame.model.ICardDealerListener;

public class RelaxModeDealingState extends RelaxModeState implements ICardDealerListener {
	public RelaxModeDealingState(RelaxModeController controller) {
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
