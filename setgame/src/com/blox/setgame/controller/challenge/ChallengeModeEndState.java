package com.blox.setgame.controller.challenge;

public class ChallengeModeEndState extends ChallengeModeState {
	public ChallengeModeEndState(ChallengeModeController controller) {
		super(controller);
	}

	@Override
	protected void activated() {
		model.endMode();
	}

	@Override
	public void onNewGame() {
		model.startMode();
		controller.setDealingState();
	}

	@Override
	public void draw() {
		model.drawResult();
	}
	
	@Override
	public void onScreenDeactivated() {
		model.exitMode();
	}
}