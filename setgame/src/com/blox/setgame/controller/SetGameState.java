package com.blox.setgame.controller;

import com.blox.setgame.model.Card;
import com.blox.setgame.model.ICardEventListener;

public abstract class SetGameState implements ICardEventListener {	
	public void activated() {
		
	}

	public void deactivated() {

	}

	@Override
	public void cardTapped(Card card) {
		
	}
}