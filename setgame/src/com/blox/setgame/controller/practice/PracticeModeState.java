package com.blox.setgame.controller.practice;

import com.blox.framework.v0.util.Game;
import com.blox.setgame.controller.SetGameState;
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
	public void onScreenDeactivated() {
		model.exitMode();
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
	public void onNewGame() {

	}

	@Override
	public void draw() {
		model.drawGame();
	}
}