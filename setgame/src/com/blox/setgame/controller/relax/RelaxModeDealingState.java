package com.blox.setgame.controller.relax;

public class RelaxModeDealingState extends RelaxModeState {
	public RelaxModeDealingState(RelaxModeController controller) {
		super(controller);
	}

	@Override
	protected void activated() {
		model.deal();
		controller.setWaitingState();
	}
}
