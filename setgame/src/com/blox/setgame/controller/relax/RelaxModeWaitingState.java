package com.blox.setgame.controller.relax;

import com.blox.framework.v0.util.Game;
import com.blox.setgame.model.Card;
import com.blox.setgame.utils.SetGameResources;

public class RelaxModeWaitingState extends RelaxModeState {
	public RelaxModeWaitingState(RelaxModeController controller) {
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
		SetGameResources.playSoundFlip();
		model.cardTapped(card);
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
		model.deselectCards();
	}
}