package com.blox.setgame.utils;

import com.blox.setgame.model.Card;

public interface ICardDealerListener {
	void startMoving(Card card);

	void stopMoving(Card card);

	void dealEnd();
}
