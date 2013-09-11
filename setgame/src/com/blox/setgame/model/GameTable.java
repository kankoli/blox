package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;

public abstract class GameTable implements  IDrawable {

	protected Card[] deck;

	// void registerSelected(SelectedState selectedState);
	//
	// void unregisterSelected(SelectedState selectedState);
	//
	public GameTable() {
		deck = Card.newDeck();
	}}
