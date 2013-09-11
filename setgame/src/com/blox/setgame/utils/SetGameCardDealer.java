package com.blox.setgame.utils;

import java.util.HashMap;
import java.util.Map;

import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.model.Card;
import com.blox.setgame.model.SetGameCards;

public class SetGameCardDealer extends CardDealer {
	// region static

	private final static int cols = 5;
	// private final static int rows = 3;
	// private final static float moveDuration = 0.25f;

	private final static Map<Integer, Vector> cardLocations = new HashMap<Integer, Vector>();

	static {
		float dy = (Game.getVirtualHeight() - Game.getVirtualWidth()) / 2f;
		for (int i = 0; i < SetGameCards.TotalCardsOnTable; i++) {
			int x = i < SetGameCards.ActiveCardCount ? i % (cols - 1) : cols - 1;
			int y = i < SetGameCards.ActiveCardCount ? i / (cols - 1) : i - SetGameCards.ActiveCardCount;

			int dx = i < SetGameCards.ActiveCardCount ? 0 : Card.Space + 1;

			cardLocations.put(i, new Vector(
					x * Card.Width + Card.Space * (x + 1) + dx,
					y * Card.Height + Card.Space * (y + 1) + dy));
		}
	}

	// endregion

	private final SetGameCards cards;
	private int index;

	public SetGameCardDealer(SetGameCards cards) {
		this.cards = cards;
	}

	@Override
	public void deal() {
		if (index == 0) {
			initCards();
		}
		else {
			removeSetCards();
			replaceEmptyCardsWithExtraCards();
			if (index < deck.length)
				dealExtraCards();
		}
		updateCards();
	}

	private void initCards() {
		for (int i = 0; i < SetGameCards.TotalCardsOnTable; i++)
			cards.setCard(deck[i], i);
		updateCards();
		index = SetGameCards.TotalCardsOnTable;
	}

	private void updateCards() {		
		for (int i = 0; i < SetGameCards.TotalCardsOnTable; i++) {
			Card card = cards.getCard(i);
			if (card != null)
				card.getLocation().set(cardLocations.get(i));
		}

		for (int i = 0; i < SetGameCards.ActiveCardCount; i++) {
			Card activeCard = cards.getActiveCard(i);
			if (activeCard != null)
				activeCard.open();
		}

		for (int i = 0; i < SetGameCards.ExtraCardCount; i++) {
			Card extraCard = cards.getExtraCard(i);
			if (extraCard != null)
				extraCard.close();
		}
	}

	private void removeSetCards() {
		cards.emptySelectedCards();
	}

	private void replaceEmptyCardsWithExtraCards() {
		int x = 0;
		for (int i = 0; i < SetGameCards.ActiveCardCount; i++) {
			if (!cards.isEmpty(i))
				continue;
			cards.setActiveCard(cards.getExtraCard(x), i);
			cards.setExtraCard(null, x);
			x++;
		}
	}

	private void dealExtraCards() {
		for (int i = 0; i < SetGameCards.SetCardCount; i++) {
			Card card = deck[index++];
			card.getLocation().set(cardLocations.get(i + SetGameCards.ActiveCardCount));
			card.close();
			cards.setExtraCard(card, i); 
		}
	}
}
