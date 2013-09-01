package com.blox.setgame.controller;

import com.blox.setgame.model.Card;
import com.blox.setgame.model.PracticeModeTable;
import com.blox.setgame.view.SetGameScreen;

public class PracticeModeController extends SetGameController {
	public PracticeModeController(SetGameScreen parent) {
		super(parent);

		gameTable = new PracticeModeTable();
		this.screen.registerDrawable(gameTable, 1);

		currState = waitingState;
		registerWaiting();
	}

	@Override
	public void cardTapped(Card card) {
		gameTable.cardTapped(card);
	}

	@Override
	public void execute() {
		super.execute();
		((PracticeModeTable) gameTable).update();
	}

	public void tap(float x, float y, int count, int button) {
		if (((PracticeModeTable) gameTable).isWaitingForContinue())
			((PracticeModeTable) gameTable).continuePlaying();
	}
}
