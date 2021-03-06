package com.turpgames.ichigu.controller.fullchallenge;

import com.turpgames.ichigu.model.game.ICardDealerListener;

public class FullChallengeModeDealingState extends FullChallengeModeState implements ICardDealerListener {
	public FullChallengeModeDealingState(FullChallengeModeController controller) {
		super(controller);
	}

	@Override
	protected void activated() {
		model.deal();
	}

	@Override
	public void onDealEnd() {
		controller.setWaitingState();
	}

	@Override
	public boolean onScreenDeactivated() {
		return false;
	}
}
