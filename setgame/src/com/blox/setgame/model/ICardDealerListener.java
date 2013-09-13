package com.blox.setgame.model;

public interface ICardDealerListener {
	void onStartMoving(Card card);

	void onStopMoving(Card card);

	void onDealEnd();
}
