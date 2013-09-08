package com.blox.setgame.controller.practice;

import com.blox.setgame.model.Card;
import com.blox.setgame.utils.SetGameResources;

public class PracticeModeBlockedState extends PracticeModeState {
	public PracticeModeBlockedState(PracticeModeController controller) {
		super(controller);
	}
	
	@Override
	public void activated() {
		model.activateCardsOnTable(controller);
	}
	
	@Override
	public void deactivated() {
		model.deactivateCards();
	}

	@Override
	public void cardTapped(Card card) {
		card.deselect();
		SetGameResources.playSoundWait();
	}
}
