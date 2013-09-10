package com.blox.setgame.controller;

import com.blox.setgame.model.Card;
import com.blox.setgame.model.ISetGameModelListener;

public abstract class SetGameState implements ISetGameModelListener {	
	public void activated() {
		
	}

	public void deactivated() {

	}

	@Override
	public void onCardTapped(Card card) {
		
	}

	@Override
	public void onSetFound() {
		
	}

	@Override
	public void onInvalidSetSelected() {
		
	}
}