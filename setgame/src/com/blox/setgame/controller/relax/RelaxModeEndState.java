package com.blox.setgame.controller.relax;

public class RelaxModeEndState extends RelaxModeState {
	public RelaxModeEndState(RelaxModeController controller) {
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
}
