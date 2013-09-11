package com.blox.setgame.model;

public class TrainingCards {
	public static final int SetCardCount = 2;
	public static final int CardToSelectCount = 3;

	private Card[] cards;

	public TrainingCards() {
		cards = new Card[SetCardCount + CardToSelectCount];
	}

	public boolean isEmpty() {
		return cards[0] != null;
	}
	
	public void empty() {
		for(int i = 0; i < cards.length; i++)
			cards[i] = null;
	}

	public int getLength() {
		return cards.length;
	}

	public Card get(int i) {
		return cards[i];
	}

	public Card[] getAllCards() {
		return cards;
	}
	
	public Card getCardsToSelect(int i) {
		return cards[SetCardCount + i];
	}

	public void setCardsOnTable(Card... cards) {
		for (int i = 0; i < SetCardCount; i++)
			this.cards[i] = cards[i];
	}

	public void setCardsToSelect(Card... cards) {
		for (int i = 0; i < CardToSelectCount; i++)
			this.cards[i + SetCardCount] = cards[i];
	}

	public int checkScore(Card selectedCard) {
		return Card.getSetScore(cards[0], cards[1], selectedCard);
	}
}