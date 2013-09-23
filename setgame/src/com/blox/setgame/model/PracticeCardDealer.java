package com.blox.setgame.model;

import com.blox.framework.v0.util.Utils;

class PracticeCardDealer extends TrainingCardDealer {
	PracticeCardDealer(TrainingCards cards) {
		super(cards);
	}

	@Override
	protected void dealNewCards() {
		int c1 = Utils.randInt(deck.length);
		int c2 = Utils.randInt(deck.length);

		while (c1 == c2)
			c2 = Utils.randInt(deck.length);

		cards.setCardsOnTable(deck[c1], deck[c2]);

		CardAttributes a1 = deck[c1].getAttributes();
		CardAttributes a2 = deck[c2].getAttributes();

		int c3 = getCompletingCardIndex(a1, a2);
		CardAttributes a3 = deck[c3].getAttributes();

		int color = a3.getColor();
		int shape = a3.getShape();
		int count = a3.getCount();
		int pattern = getChallengingAttribute(a3.getPattern());

		int c4 = findCardIndex(color, shape, count, pattern);
		int c5 = getCompletingCardIndex(a3, deck[c4].getAttributes());

		setCards(c1, c2, c3, c4, c5);
	}

	private int getChallengingAttribute(int a3) {
		return a3 == CardAttributes.Value1 ? CardAttributes.Value2 : (a3 == CardAttributes.Value2 ? CardAttributes.Value3 : CardAttributes.Value1);
	}
}
