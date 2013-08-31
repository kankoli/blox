package com.blox.set.model;

import java.util.Random;

import com.blox.framework.v0.ISound;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;
import com.blox.set.utils.R;

public class SingleGameTable2 implements Card2.ICardSelectedListener {

	private static final Random rnd = new Random();

	private ISound soundSuccess;
	private ISound soundError;

	private Card2[] deck;
	private Card2[] cardsOnTable;
	private Card2[] cardsToSelect;
	private Card2 selectedCard;

	public SingleGameTable2() {
		deck = Card2.getDeck();

		cardsOnTable = new Card2[2];
		cardsToSelect = new Card2[3];

		soundSuccess = Game.getResourceManager().getSound("success");
		soundError = Game.getResourceManager().getSound("error");

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

		if (cardsToSelect[0] != null) {
			cardsToSelect[0].deactivate();
			cardsToSelect[1].deactivate();
			cardsToSelect[2].deactivate();
		}

		cardsToSelect[0] = deck[c3];
		cardsToSelect[1] = deck[c4];
		cardsToSelect[2] = deck[c5];

		for (int i = 0; i < cardsToSelect.length * cardsToSelect.length; i++) {
			int x = rnd.nextInt(cardsToSelect.length);
			int y = rnd.nextInt(cardsToSelect.length);

			Card2 tmp = cardsToSelect[x];
			cardsToSelect[x] = cardsToSelect[y];
			cardsToSelect[y] = tmp;
		}

		for (int i = 0; i < cardsToSelect.length; i++) {
			Vector l = cardsToSelect[i].getLocation();
			l.x = R.learningModeScreen.layout.positions[2 + i].x;
			l.y = R.learningModeScreen.layout.positions[2 + i].y;
			cardsToSelect[i].open();
			cardsToSelect[i].activate();
			cardsToSelect[i].setSelectedListener(this);
		}
	}

	private int getCompletingCardIndex(int c1, int c2) {
		for (int i = 0; i < deck.length; i++) {
			if (i == c1 || i == c2)
				continue;
			if (Card2.isSet(deck[c1], deck[c2], deck[i]))
				return i;
		}

		return -1;
	}

	public void draw() {
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
		if (Card2.isSet(cardsOnTable[0], cardsOnTable[1], selectedCard)) {
			Game.getVibrator().vibrate(50);
			soundSuccess.play();
			deal();
		}
		else {
			Game.getVibrator().vibrate(100);
			soundError.play();
			selectedCard.switchSelected();
		}
	}

	@Override
	public void cardSelected(Card2 card) {
		if (card.isSelected()) {
			selectedCard = card;
			checkSet();
		}
	}
}
