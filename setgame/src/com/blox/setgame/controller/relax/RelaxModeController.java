package com.blox.setgame.controller.relax;

import com.blox.setgame.controller.SetGameController;
import com.blox.setgame.model.RelaxMode;
import com.blox.setgame.view.RelaxModeScreen;

public class RelaxModeController extends SetGameController<RelaxModeState> implements IRelaxModeActionListener {
	final RelaxMode model;
	final RelaxModeScreen view;

	private RelaxModeState waitingState;
	private RelaxModeState dealingState;
	private RelaxModeState pausedState;
	private RelaxModeState endState;

	public RelaxModeController(RelaxModeScreen screen) {
		this.view = screen;

		this.model = new RelaxMode();
		this.model.setModelListener(this);

		waitingState = new RelaxModeWaitingState(this);
		dealingState = new RelaxModeDealingState(this);
		pausedState = new RelaxModePausedState(this);
		endState = new RelaxModeEndState(this);
	}

	@Override
	public void onScreenActivated() {
		if (currentState == null) {
			model.startMode();
			setDealingState();
		}
		else {
			super.onScreenActivated();
		}
	}

	@Override
	public void onModeEnd() {
		currentState.onModeEnd();
	}

	@Override
	public void onNewGame() {
		currentState.onNewGame();
	}

	void setDealingState() {
		setState(dealingState);
	}

	void setWaitingState() {
		setState(waitingState);
	}

	void setPausedState() {
		setState(pausedState);
	}

	void setEndState() {
		setState(endState);
	}
}