package com.blox.setgame.model;

import com.blox.setgame.utils.R;

public abstract class TrainingGame extends SetGameTable {
	protected TrainingCards cards;

	protected TrainingGame() {
		cards = new TrainingCards();
	}

	public TrainingCards getCards() {
		return cards;
	}
	
	@Override
	protected Card[] getCardsOnTable() {
		return cards.getCards();
	}

	public void activateCardsOnTable(ICardEventListener listener) {
		for (int i = 0; i < TrainingCards.CardToSelectCount; i++) {
			Card card = cards.getCardToSelect(i);
			card.activate();
			card.setEventListener(listener);
		}
	}	

	public void deactivateCards() {
		for (int i = 0; i < TrainingCards.CardToSelectCount; i++) {
			Card card = cards.getCardToSelect(i);
			if (card != null)
				card.deactivate();
		}
	}

	public void updateCardLocations() {	
		for (int i = 0; i < cards.getLength(); i++)
			cards.get(i).getLocation().set(R.learningModeScreen.layout.positions[i]);
	}
}