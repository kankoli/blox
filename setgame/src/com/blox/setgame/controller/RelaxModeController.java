package com.blox.setgame.controller;

import com.blox.setgame.model.Card;
import com.blox.setgame.model.FullGameTable;
import com.blox.setgame.view.RelaxModeScreen;

public class RelaxModeController extends SetGameController {

	public RelaxModeController(RelaxModeScreen parent) {
		super(parent);

		gameTable = new FullGameTable();
		this.screen.registerDrawable(gameTable, 1);

		currState = waitingState;
		registerWaiting();
	}

	@Override
	public void cardTapped(Card card) {
		gameTable.cardTapped(card);
	}
}