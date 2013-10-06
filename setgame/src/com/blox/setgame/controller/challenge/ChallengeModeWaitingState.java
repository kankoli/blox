package com.blox.setgame.controller.challenge;

import com.blox.framework.v0.util.Game;
import com.blox.setgame.model.Card;
import com.blox.setgame.utils.SetGameResources;

public class ChallengeModeWaitingState extends ChallengeModeState {
	public ChallengeModeWaitingState(ChallengeModeController controller) {
		super(controller);
	}

	@Override
	protected void activated() {
		model.activateCards();
	}

	@Override
	protected void deactivated() {
		model.deactivateCards();
	}

	@Override
	public void onCardTapped(Card card) {
		Game.vibrate(50);
		SetGameResources.playSoundFlip();
		model.cardTapped(card);
	}

	@Override
	public void onSetFound() {
		SetGameResources.playSoundSuccess();
		Game.vibrate(100);
		controller.setDealingState();
	}

	@Override
	public void onInvalidSetSelected() {
		SetGameResources.playSoundError();
		Game.vibrate(0, 50, 50, 100);
		model.deselectCards();
	}
}