package com.blox.setgame.model;

import com.blox.setgame.utils.R;
import com.blox.setgame.utils.TrainingCardDealer;

public abstract class TrainingMode extends SetGameMode {
	protected TrainingCards cards;

	protected TrainingMode() {
		cards = new TrainingCards();
		setDealer(new TrainingCardDealer(cards));
	}
	
	@Override
	protected Card[] getCardsOnTable() {
		return cards.getAllCards();
	}

	public TrainingCards getCards() {
		return cards;
	}
	
	public void checkSet(Card selectedCard) {
		int score = cards.checkScore(selectedCard);
		if (score > 0)
			notifySetFound();
		else
			notifyInvalidSetSelected();
	}

	public void activateCardsOnTable() {
		for (int i = 0; i < TrainingCards.CardToSelectCount; i++) {
			Card card = cards.getCardsToSelect(i);
			card.activate();
			card.setEventListener(gameListener);
		}
	}	

	public void deactivateCards() {
		for (int i = 0; i < TrainingCards.CardToSelectCount; i++) {
			Card card = cards.getCardsToSelect(i);
			if (card != null)
				card.deactivate();
		}
	}

	public void updateCardLocations() {	
		for (int i = 0; i < cards.getLength(); i++)
			cards.get(i).getLocation().set(R.learningModeScreen.layout.positions[i]);
	}
	
	public void exitMode() {
		deactivateCards();
		cards.empty();
	}
}