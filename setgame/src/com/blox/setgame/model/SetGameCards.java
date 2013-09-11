package com.blox.setgame.model;

public class SetGameCards {
	public static final int ActiveCardCount = 12;
	public static final int ExtraCardCount = 3;
	public static final int SetCardCount = 3;
	public static final int TotalCardsOnTable = ActiveCardCount + ExtraCardCount;

	private final Card[] allCards;
	private final Card[] extraCards;
	private final Card[] activeCards;
	private final Card[] selectedCards;

	public SetGameCards() {
		allCards = new Card[ActiveCardCount + ExtraCardCount];
		extraCards = new Card[ExtraCardCount];
		activeCards = new Card[ActiveCardCount];
		selectedCards = new Card[SetCardCount];
	}

	Card[] getAllCards() {
		return allCards;
	}
	
	public Card getCard(int i) {
		return allCards[i];
	}

	public void setCard(Card card, int i) {
		if (i < ActiveCardCount)
			setActiveCard(card, i);
		else
			setExtraCard(card, i - ActiveCardCount);
	}

	public Card getActiveCard(int i) {
		return activeCards[i];
	}

	public void setActiveCard(Card card, int i) {
		activeCards[i] = card;
		allCards[i] = card;
	}

	public Card getExtraCard(int i) {
		return extraCards[i];
	}

	public void setExtraCard(Card card, int i) {
		extraCards[i] = card;
		allCards[i + ActiveCardCount] = card;
	}

	public boolean isEmpty(int i) {
		return allCards[i] == null;
	}

	public void empty() {
		emptySelectedCards();
		empty(allCards);
		empty(activeCards);
		empty(selectedCards);
	}

	public void emptySelectedCards() {
		updateSelectedCards();
		int x = 0;
		for (int i = 0; i < allCards.length; i++) {
			if (!allCards[i].isSelected())
				continue;
			
			selectedCards[x++] = null;
			allCards[i] = null;
			if (i < SetGameCards.ActiveCardCount)
				activeCards[i] = null;
			else
				extraCards[i - SetGameCards.ActiveCardCount] = null;
		}
	}

	public int getScore() {
		updateSelectedCards();
		return Card.getSetScore(selectedCards[0], selectedCards[1], selectedCards[2]);
	}

	public void deselectCards() {
		updateSelectedCards();
		for (int i = 0; i < selectedCards.length; i++)
			selectedCards[i].deselect();
	}

	private void updateSelectedCards() {
		int x = 0;
		for (int i = 0; i < allCards.length; i++) {
			if (allCards[i].isSelected())
				selectedCards[x++] = allCards[i];
		}
	}

	private static void empty(Card[] array) {
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}
}