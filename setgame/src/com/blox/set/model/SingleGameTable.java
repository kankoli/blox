package com.blox.set.model;

import java.util.Random;

import com.blox.framework.v0.util.Vector;
import com.blox.set.controller.SelectedState;
import com.blox.set.controller.WaitingState;
import com.blox.set.utils.R;

public class SingleGameTable extends TableObject {

	private static final Random rnd = new Random();
	
	private Card[] deck;
	private Card[] cardsOnTable;
	private Card[] cardsToSelect;
	private Card selectedCard;

	public SingleGameTable() {
		deck = Card.getDeck();

		cardsOnTable = new Card[2];
		cardsToSelect = new Card[3];
		deal();
	}

	private void deal() {
		int c1 = rnd.nextInt(deck.length);
		int c2 = rnd.nextInt(deck.length);

		while (c1 == c2)
			c2 = rnd.nextInt(deck.length);

		cardsOnTable[0] = deck[c1];
		cardsOnTable[1] = deck[c2];

		for (int i = 0; i < cardsOnTable.length; i++) {
			Vector l = cardsOnTable[i].getLocation();
			l.x = R.learningModeScreen.layout.positions[i].x;
			l.y = R.learningModeScreen.layout.positions[i].y;
			cardsOnTable[i].open();
		}

		int c3 = getCompletingCardIndex(c1, c2);
		int c4 = rnd.nextInt(deck.length);
		int c5 = rnd.nextInt(deck.length);

		while (c4 == c1 || c4 == c2 || c4 == c3)
			c4 = rnd.nextInt(deck.length);
		while (c5 == c1 || c5 == c2 || c5 == c3 || c5 == c4)
			c5 = rnd.nextInt(deck.length);
		
		cardsToSelect[0] = deck[c3];
		cardsToSelect[1] = deck[c4];
		cardsToSelect[2] = deck[c5];
		
		for (int i = 0; i < cardsToSelect.length * cardsToSelect.length; i++) {
			int x = rnd.nextInt(cardsToSelect.length);
			int y = rnd.nextInt(cardsToSelect.length);

			Card tmp = cardsToSelect[x];
			cardsToSelect[x] = cardsToSelect[y];
			cardsToSelect[y] = tmp;
		}

		for (int i = 0; i < cardsToSelect.length; i++) {
			Vector l = cardsToSelect[i].getLocation();
			l.x = R.learningModeScreen.layout.positions[2 + i].x;
			l.y = R.learningModeScreen.layout.positions[2 + i].y;
			cardsToSelect[i].open();
			cardsToSelect[i].activate();
		}
	}

	private int getCompletingCardIndex(int c1, int c2) {
		for (int i = 0; i < deck.length; i++) {
			if (i == c1 || i == c2)
				continue;
			if (Card.isSet(deck[c1], deck[c2], deck[i]))
				return i;
		}

		return -1;
	}

	@Override
	public void draw() {
		super.draw();

		drawCardsOnTable();
		drawCardsToSelect();
	}

	private void drawCardsOnTable() {
		for (int i = 0; i < cardsOnTable.length; i++) {
			cardsOnTable[i].draw();
		}
	}

	private void drawCardsToSelect() {
		for (int i = 0; i < cardsToSelect.length; i++) {
			cardsToSelect[i].draw();
		}
	}

	private void checkSet() {
		if (Card.isSet(cardsOnTable[0], cardsOnTable[1], selectedCard)) {
			deal();
		}
		else {
			selectedCard.switchSelected();
		}
	}

	@Override
	public void registerWaiting(WaitingState waitingState) {
		for (int i = 0; i < 3; i++) {
			cardsToSelect[i].registerInputEventListener(waitingState);
		}
	}

	@Override
	public void unregisterWaiting(WaitingState waitingState) {
		for (int i = 0; i < 3; i++) {
			cardsToSelect[i].registerInputEventListener(waitingState);
		}
	}

	@Override
	public void registerSelected(SelectedState selectedState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterSelected(SelectedState selectedState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cardSelected(Card card) {
		selectedCard = card;
		checkSet();
	}

	@Override
	public void cardUnselected(Card card) {
		// TODO Auto-generated method stub

	}
}
