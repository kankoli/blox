package com.blox.setgame.controller;

import com.blox.setgame.model.Card;
import com.blox.setgame.model.LearningModeTable;
import com.blox.setgame.view.SetGameScreen;

public class LearningModeController extends SetGameController {

	public LearningModeController(SetGameScreen parent) {
		super(parent);
		
		gameTable = new LearningModeTable();
		this.screen.registerDrawable(gameTable, 1);
		
		currState = waitingState;
		gameTable.registerWaiting(waitingState);
	}

	@Override
	public void cardTapped(Card card) {
		gameTable.cardTapped(card);
	}
}
