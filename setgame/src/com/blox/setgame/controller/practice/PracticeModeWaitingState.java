package com.blox.setgame.controller.practice;

import com.blox.framework.v0.util.Game;
import com.blox.setgame.model.Card;
import com.blox.setgame.utils.SetGameResources;

public class PracticeModeWaitingState extends PracticeModeState {
	public PracticeModeWaitingState(PracticeModeController controller) {
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
		model.checkSet(card);
	}

	@Override
	public void onSetFound() {
		SetGameResources.playSoundSuccess();
		Game.vibrate(50);
		controller.setDealingState();
	}

	@Override
	public void onInvalidSetSelected() {
		SetGameResources.playSoundError();
		Game.vibrate(100);
		controller.setBlockedState();
	}
}