package com.blox.setgame.controller.practice;

import com.blox.setgame.controller.ISetGameController;
import com.blox.setgame.model.Card;
import com.blox.setgame.model.PracticeGame;
import com.blox.setgame.view.PracticeModeScreen;

public class PracticeModeController implements ISetGameController, IPracticeModeEventListener {
	final Card[] deck;
	final PracticeGame model;
	final PracticeModeScreen view;

	private PracticeModeState waitingState;
	private PracticeModeState dealingState;
	private PracticeModeState blockedState;
	private PracticeModeState modeEndState;
	private PracticeModeState currentState;

	public PracticeModeController(PracticeModeScreen screen) {
		view = screen;
		model = new PracticeGame();
		model.setEventListener(this);		
		deck = Card.newDeck();
		waitingState = new PracticeModeWaitingState(this);
		dealingState = new PracticeModeDealingState(this);
		blockedState = new PracticeModeBlockedState(this);
		modeEndState = new PracticeModeEndState(this);
	}

	@Override
	public void activated() {
		model.startGame();
		setDealingState();
		view.registerDrawable(model, 1);
	}

	@Override
	public void deactivated() {
		currentState.deactivated();
		model.deactivateCards();
		model.exitGame();
		view.unregisterDrawable(model);
	}

	@Override
	public boolean screenTapped() {
		return currentState.screenTapped();
	}

	@Override
	public void cardTapped(Card card) {
		currentState.cardTapped(card);
	}

	@Override
	public void unblocked() {
		currentState.unblocked();
	}

	@Override
	public void dealTimeUp() {
		currentState.dealTimeUp();
	}
	
	@Override
	public void timeUp() {
		currentState.timeUp();
	}
	
	void setDealingState() {
		setState(dealingState);
	}

	void setWaitingState() {
		setState(waitingState);
	}

	void setBlockedState() {
		setState(blockedState);
	}

	void setModeEndState() {
		setState(modeEndState);
	}

	private void setState(PracticeModeState newState) {
		if (currentState != null)
			currentState.deactivated();
		currentState = newState;
		currentState.activated();
	}
}