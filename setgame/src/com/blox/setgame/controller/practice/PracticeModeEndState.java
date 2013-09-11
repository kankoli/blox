package com.blox.setgame.controller.practice;

import com.blox.framework.v0.util.Game;

public class PracticeModeEndState extends PracticeModeState {
	public PracticeModeEndState(PracticeModeController controller) {
		super(controller);
	}
	
	@Override
	protected void activated() {
		model.endMode();
		Game.vibrate(100);
	}
	
	@Override
	public boolean onScreenTapped() {
		model.startMode();
		controller.setDealingState();
		return true;
	}
}
