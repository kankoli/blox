package com.blox.setgame.controller;

import com.blox.setgame.model.Card;

public abstract class SetGameState implements ISetGameController {	
	protected void activated() {
		
	}

	protected void deactivated() {

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

	@Override
	public void onScreenActivated() {
		
	}

	@Override
	public void onScreenDeactivated() {
		this.deactivated();
	}	
}