package com.blox.set.model;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class GameTable extends GameObject {
	private static final int rows = 3;
	private static final int cols = 4 + 1;
	private static final int setCardCount = 3;
	private static final int numberOfCards = rows * cols;

	private static final int TableHeight = 550;
	
	private Card[] deck;
	private Card[][] cardsOnTable; // [cols][rows]. holds extra cards on the last column (index cols-1)
	private List<Card> selectedCards;
	private int index = 0;

	public GameTable() {
		deck = Card.getDeck(this);
		selectedCards = new ArrayList<Card>();

		initCardsOnTable();
		
		printSets();
	}

	private void printSets() {
		List<Card[]> sets = findSets();
		System.out.println("# of sets: " + sets.size());
		for (Card[] set : sets) {
			System.out.print(set[0].getAttributes() + " " + set[1].getAttributes() + " " + set[2].getAttributes() + "   ");
		}
		System.out.println();
	}

	public void openExtraCards() {
		for (int i = 0; i < 3; i++) {
			cardsOnTable[cols-1][i].open();
		}
	}

	public List<Card[]> findSets() {
		List<Card[]> sets = new ArrayList<Card[]>();
		for (int i = 0; i < numberOfCards; i++) {
			for (int j = i+1; j < numberOfCards; j++) {
				CardAttributes third = CardAttributes.getThirdCardAttributes(cardsOnTable[i/rows][i%rows].getAttributes(),
						cardsOnTable[j/rows][j%rows].getAttributes());
				for (int k = j+1; k < numberOfCards; k++) {
					CardAttributes cue = cardsOnTable[k/rows][k%rows].getAttributes();
					if (cue.equals(third))
					{
						sets.add(new Card[]{cardsOnTable[i/rows][i%rows],
								cardsOnTable[j/rows][j%rows],
								cardsOnTable[k/rows][k%rows]});
					}
				}
			}
		}
		return sets;
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
		
		printSets();
	}

	private void initCardsOnTable() {
		cardsOnTable = new Card[cols][rows];
		for (int i = 0; i < cols-1; i++) {
			cardsOnTable[i] = new Card[rows];
			for (int j = 0; j < rows; j++) {
				cardsOnTable[i][j] = deck[i * rows + j];
				Vector l = cardsOnTable[i][j].getLocation();
				l.x = i * Card.Width + Card.Space * (i + 1);
				l.y = j * Card.Height + Card.Space * (j + 1)
						+ (Game.getViewportHeight() - TableHeight)/2;
//				System.out.println("x: " + l.x + " y: " + l.y);
				cardsOnTable[i][j].open();
				cardsOnTable[i][j].activate();
			}
		}
		
		cardsOnTable[cols-1] = new Card[rows];

		index = rows * (cols-1);
		
		updateExtraCards();
		
	}
	
	private void updateCardsOnTable() {
		for (int i = 0; i < cols-1; i++) {
			for (int j = 0; j < rows; j++) {
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
		for (int i = 0; i < 3; i++) {
			if (cardsOnTable[cols-1][i] != null) 
				cardsOnTable[cols-1][i].deactivate();
			
			cardsOnTable[cols-1][i] = deck[index + i];
			
			Vector location = cardsOnTable[cols-1][i].getLocation();
			location.x = (cols-1) * Card.Width + Card.Space * (cols + 1) + 1;
			location.y = i * Card.Height + Card.Space * (i + 1)
					+ (Game.getViewportHeight() - TableHeight)/2;
			
			cardsOnTable[cols-1][i].activate();
		}
		
		index += 3;
	}

	private Card getUnselectedExtraCard() {
		for (int i = 0; i < 3; i++) {
			if (cardsOnTable[cols-1][i] != null && !cardsOnTable[cols-1][i].isSelected()) {
				Card extraCard = cardsOnTable[cols-1][i];
				cardsOnTable[cols-1][i] = null;
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
		for (int i = 0; i < cols-1; i++) {
			for (int j = 0; j < rows; j++) {
				cardsOnTable[i][j].draw();
			}
		}
	}
	
	private void drawExtraCards() {
		for (int i = 0; i < 3; i++) {
			cardsOnTable[cols-1][i].draw();
		}
	}
}
