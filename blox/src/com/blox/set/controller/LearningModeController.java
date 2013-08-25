package com.blox.set.controller;

import com.blox.set.model.Card;
import com.blox.set.model.SingleGameTable;
import com.blox.set.view.LearningModeScreen;

public class LearningModeController extends SetGameController {

	public LearningModeController(LearningModeScreen parent) {
		super(parent);
		
		gameTable = new SingleGameTable();
		this.screen.registerDrawable(gameTable, 1);
		
		currState = waitingState;
		registerWaiting();
	}

	@Override
	public void tapped(Card card) {
		unregisterWaiting();
		card.switchSelected();
		if (card.isSelected()) {
			gameTable.cardSelected(card);
		}
		else {
			gameTable.cardUnselected(card);
		}
		registerWaiting();
	}
}
