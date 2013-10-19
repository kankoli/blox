package com.turpgames.ichigu.controller.fullchallenge;

import com.turpgames.framework.v0.util.Game;
import com.turpgames.ichigu.model.Card;
import com.turpgames.ichigu.utils.IchiguResources;

public class FullChallengeModeWaitingState extends FullChallengeModeState {
	public FullChallengeModeWaitingState(FullChallengeModeController controller) {
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
		IchiguResources.playSoundFlip();
		model.cardTapped(card);
	}

	@Override
	public void onIchiguFound() {
		IchiguResources.playSoundSuccess();
		Game.vibrate(100);
		controller.setDealingState();
	}

	@Override
	public void onInvalidIchiguSelected() {
		IchiguResources.playSoundError();
		Game.vibrate(0, 50, 50, 100);
		model.deselectCards();
	}
}