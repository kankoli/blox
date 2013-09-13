package com.blox.setgame.controller.practice;

import com.blox.setgame.model.Card;
import com.blox.setgame.model.ICardDealerListener;

public class PracticeModeDealingState extends PracticeModeState implements ICardDealerListener {
	public PracticeModeDealingState(PracticeModeController controller) {
		super(controller);
		model.setDealerListener(this);
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
		model.dealEnd();
		controller.setWaitingState();
	}
}