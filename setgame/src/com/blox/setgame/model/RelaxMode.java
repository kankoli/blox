package com.blox.setgame.model;

import com.blox.setgame.utils.SetGameCardDealer;

public class RelaxMode extends SetGameMode {
	private SetGameCards cards;
	private int selectedCardCount;

	public RelaxMode() {
		cards = new SetGameCards();
		setDealer(new SetGameCardDealer(cards));
	}

	protected Card[] getCardsOnTable() {
		return cards.getAllCards();
	}

	public SetGameCards getCards() {
		return cards;
	}

	public void activateCards() {
		for (int i = 0; i < SetGameCards.TotalCardsOnTable; i++) {
			Card card = cards.getCard(i);
			if (card != null) {
				card.activate();
				card.setEventListener(gameListener);
			}
		}
	}

	public void deactivateCards() {
		for (int i = 0; i < SetGameCards.TotalCardsOnTable; i++) {
			Card card = cards.getCard(i);
			if (card != null) {
				card.deactivate();
				card.setEventListener(null);
			}
		}
	}

	public void exitMode() {
		deactivateCards();
		cards.empty();
	}

	public void cardTapped(Card card) {
		if (!card.isOpened()) {
			card.deselect();
			for (int i = 0; i < SetGameCards.ExtraCardCount; i++)
				cards.getExtraCard(i).open();
			return;
		}

		if (card.isSelected())
			selectedCardCount++;
		else
			selectedCardCount--;

		if (selectedCardCount == SetGameCards.SetCardCount) {
			int score = cards.getScore();
			if (score > 0)
				notifySetFound();
			else
				notifyInvalidSetSelected();
			selectedCardCount = 0;
		}
	}

	public void deselectCards() {
		cards.deselectCards();
	}

	public void startMode() {
		Card[] allCards = cards.getAllCards();
		for (int i = 0; i < allCards.length; i++) {
			if (allCards[i] != null)
				allCards[i].deselect();
		}

		cards.empty();
		((SetGameCardDealer) dealer).reset();
	}
}