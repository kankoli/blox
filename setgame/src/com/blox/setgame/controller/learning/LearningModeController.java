package com.blox.setgame.controller.learning;

import com.blox.setgame.controller.ISetGameController;
import com.blox.setgame.model.Card;
import com.blox.setgame.model.LearningGame;
import com.blox.setgame.view.LearningModeScreen;

public class LearningModeController implements ISetGameController {
	final Card[] deck;
	final LearningGame model;
	final LearningModeScreen view;

	private LearningModeState waitingState;
	private LearningModeState dealingState;
	private LearningModeState currentState;

	public LearningModeController(LearningModeScreen screen) {
		view = screen;
		model = new LearningGame();
		deck = Card.newDeck();
		waitingState = new LearningModeWaitingState(this);
		dealingState = new LearningModeDealingState(this);
	}

	@Override
	public void activated() {
		setDealingState();
		view.registerDrawable(model, 1);
	}

	@Override
	public void deactivated() {
		currentState.deactivated();
		model.deactivateCards();
		view.unregisterDrawable(model);
	}
	
	@Override
	public void cardTapped(Card card) {
		currentState.cardTapped(card);
	}
	
	void setDealingState() {
		setState(dealingState);
	}
	
	void setWaitingState() {
		setState(waitingState);
	}

	private void setState(LearningModeState newState) {
		if (currentState != null)
			currentState.deactivated();
		currentState = newState;
		currentState.activated();
	}
}