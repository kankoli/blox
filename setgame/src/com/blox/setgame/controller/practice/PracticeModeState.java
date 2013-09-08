package com.blox.setgame.controller.practice;

import com.blox.setgame.controller.SetGameState;
import com.blox.setgame.model.Card;
import com.blox.setgame.model.PracticeGame;
import com.blox.setgame.view.PracticeModeScreen;

public abstract class PracticeModeState extends SetGameState implements IPracticeModeEventListener {
	final PracticeGame model;
	final PracticeModeScreen view;
	final PracticeModeController controller;

	public PracticeModeState(PracticeModeController controller) {
		this.controller = controller;
		this.model = controller.model;
		this.view = controller.view;
	}

	@Override
	public void cardTapped(Card card) {

	}

	@Override
	public boolean screenTapped() {
		return false;	
	}

	@Override
	public void unblocked() {
		controller.setWaitingState();
	}

	@Override
	public void dealTimeUp() {
		controller.setDealingState();
	}
	
	@Override
	public void timeUp() {
		controller.setModeEndState();
	}
}
