package com.blox.setgame.controller;

import com.blox.setgame.model.Card;

public abstract class SetGameController<T extends SetGameState> implements ISetGameController {
	protected T currentState;

	@Override
	public void onCardTapped(Card card) {
		currentState.onCardTapped(card);
	}

	@Override
	public void onScreenActivated() {
		if (currentState != null)
			currentState.onScreenActivated();
	}

	@Override
	public void onScreenDeactivated() {
		currentState.onScreenDeactivated();
	}

	@Override
	public void onSetFound() {
		currentState.onSetFound();
	}

	@Override
	public void onInvalidSetSelected() {
		currentState.onInvalidSetSelected();
	}

	@Override
	public void draw() {
		currentState.draw();
	}

	protected void setState(T newState) {
		if (currentState != null)
			currentState.deactivated();
		currentState = newState;
		currentState.activated();
	}
}