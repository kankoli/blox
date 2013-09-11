package com.blox.setgame.controller.practice;

import com.blox.setgame.model.Card;
import com.blox.setgame.utils.SetGameResources;

public class PracticeModeBlockedState extends PracticeModeState {
	public PracticeModeBlockedState(PracticeModeController controller) {
		super(controller);
	}
	
	@Override
	protected void activated() {
		model.activateCardsOnTable();
	}
	
	@Override
	protected void deactivated() {
		model.deactivateCards();
	}

	@Override
	public void onCardTapped(Card card) {
		card.deselect();
		SetGameResources.playSoundWait();
	}
}
