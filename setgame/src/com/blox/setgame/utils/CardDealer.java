package com.blox.setgame.utils;

import com.blox.setgame.model.Card;

public abstract class CardDealer {
	protected final Card[] deck;
	protected ICardDealerListener listener;

	protected CardDealer() {
		deck = Card.newDeck();
	}

	public abstract void deal();

	public void setDealingListener(ICardDealerListener listener) {
		this.listener = listener;
	}

	protected void notifyDealEnd() {
		if (listener != null)
			listener.onDealEnd();
	}

	protected void notifyStartMoving(Card card) {
		if (listener != null)
			listener.onStartMoving(card);
	}

	protected void notifyStopMoving(Card card) {
		if (listener != null)
			listener.onStopMoving(card);
	}
}
