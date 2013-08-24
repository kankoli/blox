package com.blox.set.model;

import java.util.ResourceBundle;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Vector;
import com.blox.set.util.R;

public class SingleGameTable extends GameObject {

	private ResourceBundle bundle;
	
	private Card[] deck;
	private Card[] cardsOnTable;
	private Card[] cardsToSelect;
	private Card selectedCard;
	
	private int index = 0;
	
	public SingleGameTable() {
		deck = Card.getDeck(this);

		cardsOnTable = new Card[2];
		cardsToSelect = new Card[3];
		deal();
	}
	
	private void deal() {
		dealTheTwo();
		
		dealTheThree();
		
		index = 5;
		
		if (!thereIsSet()) {
			dealTheTwo();
			index += 2;
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
}
