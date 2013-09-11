package com.blox.setgame.utils;

import com.blox.setgame.model.Card;

public interface ICardDealerListener {
	void onStartMoving(Card card);

	void onStopMoving(Card card);

	void onDealEnd();
}
