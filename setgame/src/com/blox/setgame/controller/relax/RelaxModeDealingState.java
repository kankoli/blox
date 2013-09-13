package com.blox.setgame.controller.relax;

import com.blox.setgame.model.Card;
import com.blox.setgame.model.ICardDealerListener;
import com.blox.setgame.model.FullGameCards;

public class RelaxModeDealingState extends RelaxModeState implements ICardDealerListener {
	public RelaxModeDealingState(RelaxModeController controller) {
		super(controller);
		model.setDealerListener(this);
	}

	@Override
	protected void activated() {
		model.deal();
	}

	@Override
	protected void deactivated() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			Card card = model.getCards().getCard(i);
			if (card != null)
				view.unregisterMovable(card);
		}
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
