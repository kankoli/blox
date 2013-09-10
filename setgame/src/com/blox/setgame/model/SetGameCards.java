package com.blox.setgame.model;

public class SetGameCards {
	public static final int ActiveCardCount = 12;
	public static final int ExtraCardCount = 3;

	private final Card[] allCards;
	private final Card[] extraCards;
	private final Card[] activeCards;

	public SetGameCards() {
		allCards = new Card[ActiveCardCount + ExtraCardCount];
		extraCards = new Card[ExtraCardCount];
		activeCards = new Card[ActiveCardCount];
	}
	
	public Card[] getAllCards() {
		return allCards;
	}
	
	public Card[] getExtraCards() {
		return extraCards;
	}
	
	public Card[] getActiveCards() {
		return activeCards;
	}
}
