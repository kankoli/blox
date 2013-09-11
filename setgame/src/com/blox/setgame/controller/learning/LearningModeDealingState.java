package com.blox.setgame.controller.learning;

import com.blox.setgame.model.Card;
import com.blox.setgame.utils.ICardDealerListener;

public class LearningModeDealingState extends LearningModeState implements ICardDealerListener {
	public LearningModeDealingState(LearningModeController controller) {
		super(controller);
		this.model.getDealer().setDealingListener(this);
	}

	@Override
	protected void activated() {
		model.deal();
	}

	@Override
	protected void deactivated() {
		Card[] cc = model.getCards().getAllCards();
		for (int i = 0; i < cc.length; i++)
			view.unregisterMovable(cc[i]);
	}

	@Override
	public void onStartMoving(Card card) {
		view.registerMovable(card);
	}

	@Override
	public void onStopMoving(Card card) {
		view.unregisterMovable(card);
	}

	@Override
	public void onDealEnd() {
		controller.setWaitingState();
	}
}
