package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;
import com.blox.setgame.controller.WaitingState;

public abstract class GameTable implements Card.ICardTapListener, IDrawable {

	protected Card[] deck;

	// void registerSelected(SelectedState selectedState);
	//
	// void unregisterSelected(SelectedState selectedState);
	//
	public GameTable() {
		deck = Card.getDeck();
	}

	public void activateCards() {
		for (Card card : deck) {
			card.activate();
		}
	}

	public void deactivateCards() {
		for (Card card : deck) {
			card.deactivate();
		}
	}

	public void registerWaiting(WaitingState waitingState) {
		for (int i = 0; i < deck.length; i++) {
			deck[i].setTapListener(waitingState);
		}
	}

	public void unregisterWaiting() {
		for (int i = 0; i < deck.length; i++) {
			deck[i].unsetTapListener();
		}
	}
}
