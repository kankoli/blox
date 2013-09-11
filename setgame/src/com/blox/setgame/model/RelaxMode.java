package com.blox.setgame.model;

public class RelaxMode extends SetGameMode {
	private SetGameCards cards;
	
	public RelaxMode() {
		cards = new SetGameCards();
	}
	
	protected Card[] getCardsOnTable() {
		return cards.getAllCards();
	}
}
