package com.blox.setgame.model;


public class RelaxMode extends SetGameMode {
	private FullGameCards cards;
	private int selectedCardCount;

	public RelaxMode() {
		cards = new FullGameCards();
		dealer = new FullGameCardDealer(cards);
	}

	protected Card[] getCardsOnTable() {
		return cards.getAllCards();
	}

	public FullGameCards getCards() {
		return cards;
	}

	public void activateCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			Card card = cards.getCard(i);
			if (card != null) {
				card.activate();
				card.setEventListener(gameListener);
			}
		}
	}

	public void deactivateCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
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
			for (int i = 0; i < FullGameCards.ExtraCardCount; i++)
				cards.getExtraCard(i).open();
			return;
		}

		if (card.isSelected())
			selectedCardCount++;
		else
			selectedCardCount--;

		if (selectedCardCount == FullGameCards.SetCardCount) {
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
		cards.empty();
		dealer.reset();
	}
}