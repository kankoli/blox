package com.blox.set.controller;

import com.blox.set.model.Card;
import com.blox.set.model.FullGameTable;
import com.blox.set.view.RelaxModeScreen;

public class RelaxModeController extends SetGameController {

	public RelaxModeController(RelaxModeScreen parent) {
		super(parent);
		
		gameTable = new FullGameTable();
		this.screen.registerDrawable(gameTable, 1);
		
		currState = waitingState;
		registerWaiting();
	}

	@Override
	public void tapped(Card card) {
		if (card.isOpened()) {
			card.switchSelected();
			if (card.isSelected()) {
				gameTable.cardSelected(card);
			}
			else {
				gameTable.cardUnselected(card);
			}
		}
		else {
			((FullGameTable) gameTable).openExtraCards();
		}
	}
}