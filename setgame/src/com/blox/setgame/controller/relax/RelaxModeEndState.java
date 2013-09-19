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
	public boolean onScreenTapped() {
		model.startMode();
		controller.setDealingState();
		return true;
	}
}
