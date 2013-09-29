package com.blox.setgame.controller.practice;

import com.blox.setgame.controller.SetGameController;
import com.blox.setgame.model.Card;
import com.blox.setgame.model.PracticeMode;
import com.blox.setgame.view.PracticeModeScreen;

public class PracticeModeController extends SetGameController<PracticeModeState> implements IPracticeModeActionListener {
	final PracticeMode model;
	final PracticeModeScreen view;

	private PracticeModeState waitingState;
	private PracticeModeState dealingState;
	private PracticeModeState blockedState;
	private PracticeModeState modeEndState;

	public PracticeModeController(PracticeModeScreen screen) {
		view = screen;
		model = new PracticeMode();
		model.setModeListener(this);
		waitingState = new PracticeModeWaitingState(this);
		dealingState = new PracticeModeDealingState(this);
		blockedState = new PracticeModeBlockedState(this);
		modeEndState = new PracticeModeEndState(this);
	}

	@Override
	public void onScreenActivated() {
		model.startMode();
		setDealingState();
	}

	@Override
	public void onScreenDeactivated() {
		super.onScreenDeactivated();
		model.exitMode();
	}

	@Override
	public void onNewGame() {
		currentState.onNewGame();
	}

	@Override
	public void onCardTapped(Card card) {
		currentState.onCardTapped(card);
	}

	@Override
	public void onUnblock() {
		currentState.onUnblock();
	}

	@Override
	public void onDealTimeUp() {
		currentState.onDealTimeUp();
	}

	@Override
	public void onModeEnd() {
		currentState.onModeEnd();
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
}