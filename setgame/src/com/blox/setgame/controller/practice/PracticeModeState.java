package com.blox.setgame.controller.practice;

import com.blox.framework.v0.util.Game;
import com.blox.setgame.controller.SetGameState;
import com.blox.setgame.model.Card;
import com.blox.setgame.model.PracticeMode;
import com.blox.setgame.utils.SetGameResources;
import com.blox.setgame.view.PracticeModeScreen;

public abstract class PracticeModeState extends SetGameState implements IPracticeModeActionListener {
	final PracticeMode model;
	final PracticeModeScreen view;
	final PracticeModeController controller;

	public PracticeModeState(PracticeModeController controller) {
		this.controller = controller;
		this.model = controller.model;
		this.view = controller.view;
	}

	@Override
	public void onCardTapped(Card card) {

	}

	@Override
	public void onUnblock() {
		controller.setWaitingState();
	}

	@Override
	public void onDealTimeUp() {
		SetGameResources.playSoundTimeUp();
		Game.vibrate(100);
		controller.setDealingState();
	}

	@Override
	public void onModeEnd() {
		controller.setModeEndState();
	}

	@Override
	public void onSetFound() {

	}

	@Override
	public void onInvalidSetSelected() {

	}

	@Override
	public boolean onScreenTapped() {
		return false;
	}

	@Override
	public void onScreenActivated() {
		
	}

	@Override
	public void onScreenDeactivated() {
		this.deactivated();
	}
}
