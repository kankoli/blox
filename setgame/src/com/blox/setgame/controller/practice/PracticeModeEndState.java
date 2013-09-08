package com.blox.setgame.controller.practice;

import com.blox.framework.v0.util.Game;

public class PracticeModeEndState extends PracticeModeState {
	public PracticeModeEndState(PracticeModeController controller) {
		super(controller);
	}
	
	@Override
	public void activated() {
		model.endGame();
		model.deactivateCards();
		Game.vibrate(100);
	}
	
	@Override
	public boolean screenTapped() {
		controller.setDealingState();
		model.startGame();
		return true;
	}
}
