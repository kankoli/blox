package com.blox.set.model;

import com.blox.framework.v0.util.Vector;
import com.blox.set.controller.SelectedState;
import com.blox.set.controller.WaitingState;
import com.blox.set.utils.R;

public class SingleGameTable extends TableObject {
	
	private Card[] deck;
	private Card[] cardsOnTable;
	private Card[] cardsToSelect;
	private Card selectedCard;
	
	private int index = 0;
	
	public SingleGameTable() {
		deck = Card.getUnshuffledDeck();

		cardsOnTable = new Card[2];
		cardsToSelect = new Card[3];
		deal();
	}
	
	private void deal() {
		dealTheTwo();
		
		dealTheThree();
				
		while (!thereIsSet()) {
			dealTheTwo();
		}
	}

	private void dealTheTwo() {
		for (int i = 0; i < 2; i++) {
			cardsOnTable[i] = deck[i + index];
			Vector l = cardsOnTable[i].getLocation();
			l.x = R.learningModeScreen.layout.positions[i].x;
			l.y = R.learningModeScreen.layout.positions[i].y;
//				System.out.println("x: " + l.x + " y: " + l.y);
			cardsOnTable[i].open();
			cardsOnTable[i].activate();
		}

		index += 2;
	}
	
	private void dealTheThree() {
		for (int i = 0; i < 3; i++) {
			cardsToSelect[i] = deck[i + index];
			Vector l = cardsToSelect[i].getLocation();
			l.x = R.learningModeScreen.layout.positions[2+i].x;
			l.y = R.learningModeScreen.layout.positions[2+i].y;
//				System.out.println("x: " + l.x + " y: " + l.y);
			cardsToSelect[i].open();
			cardsToSelect[i].activate();
		}

		index += 3;
	}

	private boolean thereIsSet() {
		CardAttributes third = CardAttributes.getThirdCardAttributes(cardsOnTable[0].getAttributes(),
				cardsOnTable[1].getAttributes());
		for (int i = 0; i < 3; i++) {
			CardAttributes cue = cardsToSelect[i].getAttributes();
			if (cue.equals(third))
				return true;
		}
		return false;
	}
	
	@Override
	public void draw() {
		super.draw();
		
		drawCardsOnTable();		
		// TODO: drawSeperator();
		drawCardsToSelect();
	}

	private void drawCardsOnTable() {
		for (int i = 0; i < 2; i++) {
				cardsOnTable[i].draw();
		}
	}
	
	private void drawCardsToSelect() {
		for (int i = 0; i < 3; i++) {
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
