package com.blox.setgame.controller.challenge;

import com.blox.setgame.controller.SetGameState;
import com.blox.setgame.model.ChallengeMode;
import com.blox.setgame.view.ChallengeModeScreen;

public abstract class ChallengeModeState extends SetGameState implements IChallengeModeActionListener {
	final ChallengeMode model;
	final ChallengeModeScreen view;
	final ChallengeModeController controller;

	public ChallengeModeState(ChallengeModeController controller) {
		this.controller = controller;
		this.model = controller.model;
		this.view = controller.view;
	}

	@Override
	public void onModeEnd() {
		controller.setEndState();
	}

	@Override
	public void onNewGame() {

	}
	
	@Override
	public void draw() {
		model.drawGame();
	}

	@Override
	public void onScreenDeactivated() {
		model.exitMode();
	}
}