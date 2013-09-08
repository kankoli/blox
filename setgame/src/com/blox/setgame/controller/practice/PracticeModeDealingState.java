package com.blox.setgame.controller.practice;

import com.blox.setgame.model.Card;
import com.blox.setgame.utils.ICardDealerListener;
import com.blox.setgame.utils.TrainingCardDealer;

public class PracticeModeDealingState extends PracticeModeState implements ICardDealerListener {
	private final TrainingCardDealer dealer;

	public PracticeModeDealingState(PracticeModeController controller) {
		super(controller);
		this.dealer = new TrainingCardDealer(controller.deck, controller.model.getCards());
		this.dealer.setDealingListener(this);
	}

	@Override
	public void activated() {
		if (model.requiresNewDeal()) {
			model.dealStart();
			dealer.deal();
		}
		else {
			controller.setModeEndState();
		}
	}

	@Override
	public void deactivated() {
		Card[] cc = model.getCards().getCards();
		for (int i = 0; i < cc.length; i++)
			view.unregisterMovable(cc[i]);
	}

	@Override
	public void startMoving(Card card) {
		view.registerMovable(card);
	}

	@Override
	public void stopMoving(Card card) {
		view.unregisterMovable(card);
	}

	@Override
	public void dealEnd() {
		model.dealEnd();
		controller.setWaitingState();
	}
}