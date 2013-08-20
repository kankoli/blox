package com.blox.set.model;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class GameTable extends GameObject {
	private static final int rows = 3;
	private static final int cols = 4;
	private static final int setCardCount = 3;

	private static final int TableWidth = 550;
	private static final int TableHeight = 550;
	
	private Card[] deck;
	private Card[][] cardsOnTable;
	private Card[] extraCards;
	private List<Card> selectedCards;
	private int index = 0;

	public GameTable() {
		deck = Card.getDeck(this);
		selectedCards = new ArrayList<Card>();

		initCardsOnTable();
		
		extraCards = new Card[rows];
		updateExtraCards();
	}

	public void openExtraCards() {
		for (int i = 0; i < extraCards.length; i++) {
			extraCards[i].open();
		}
	}

	public void checkSet() {
		int selectedCardCount = selectedCards.size();
		if (selectedCardCount != setCardCount)
			return;

		if (Card.isSet(selectedCards.get(0), selectedCards.get(1), selectedCards.get(2))) {
			updateCardsOnTable();
			updateExtraCards();
		}
		else {
			for (int i = selectedCards.size() - 1; i >= 0; i--)
				selectedCards.get(i).switchSelected();
		}
	}

	private void initCardsOnTable() {
		cardsOnTable = new Card[rows][cols];
		for (int i = 0; i < rows; i++) {
			cardsOnTable[i] = new Card[cols];
			for (int j = 0; j < cols; j++) {
				cardsOnTable[i][j] = deck[i * cols + j];
				Vector l = cardsOnTable[i][j].getLocation();
				l.x = j * Card.Width + Card.Space * (j + 1);
				l.y = i * Card.Height + Card.Space * (i + 1)
						+ (Game.getViewportHeight() - TableHeight)/2;
//				System.out.println("x: " + location.x + " y: " + location.y);
				cardsOnTable[i][j].open();
				cardsOnTable[i][j].activate();
			}
		}

		index = rows * cols;
	}
	
	private void updateCardsOnTable() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Card card = cardsOnTable[i][j];
				if (card.isSelected()) {
					Card extraCard = getUnselectedExtraCard();
					extraCard.getLocation().set(card.getLocation());
					extraCard.open();
					cardsOnTable[i][j] = extraCard;
					
					card.deactivate();
					selectedCards.remove(card);
				}
			}
		}
	}

	private void updateExtraCards() {
		// TODO: deck bitmisse
		for (int i = 0; i < extraCards.length; i++) {
			if (extraCards[i] != null) 
				extraCards[i].deactivate();
			
			extraCards[i] = deck[index + i];
			
			Vector location = extraCards[i].getLocation();
			location.x = cols * Card.Width + Card.Space * (cols + 2) + 1;
			location.y = i * Card.Height + Card.Space * (i + 1)
					+ (Game.getViewportHeight() - TableHeight)/2;
			
			extraCards[i].activate();
		}
		
		index += extraCards.length;
	}

	private Card getUnselectedExtraCard() {
		for (int i = 0; i < extraCards.length; i++) {
			if (extraCards[i] != null && !extraCards[i].isSelected()) {
				Card extraCard = extraCards[i];
				extraCards[i] = null;
				return extraCard;
			}
		}
		// TODO: dusun bunu
		return null;
	}

	public void cardSelected(Card card) {
		selectedCards.add(card);
		checkSet();
	}

	public void cardUnselected(Card card) {
		selectedCards.remove(card);
	}
	
	@Override
	public void draw() {
		super.draw();
		
		drawCardsOnTable();		
		// TODO: drawSeperator();		
		drawExtraCards();
	}

	private void drawCardsOnTable() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cardsOnTable[i][j].draw();
			}
		}
	}

	private void drawExtraCards() {
		for (int i = 0; i < extraCards.length; i++) {
			extraCards[i].draw();
		}
	}
}
