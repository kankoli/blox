package com.blox.setgame.controller.relax;

import com.blox.setgame.controller.SetGameController;
import com.blox.setgame.model.RelaxMode;
import com.blox.setgame.view.RelaxModeScreen;

public class RelaxModeController extends SetGameController<RelaxModeState> {
	final RelaxMode model;
	final RelaxModeScreen view;
	
	private RelaxModeState waitingState;
	private RelaxModeState dealingState;

	public RelaxModeController(RelaxModeScreen screen) {
		this.view = screen;
		
		this.model = new RelaxMode();
		this.model.setGameListener(this);

		waitingState = new RelaxModeWaitingState(this);
		dealingState = new RelaxModeDealingState(this);
	}

	@Override
	public void onScreenActivated() {
		model.startMode();
		setDealingState();
		view.registerDrawable(model, 1);
	}

	@Override
	public void onScreenDeactivated() {
		super.onScreenDeactivated();
		model.exitMode();
		view.unregisterDrawable(model);
	}
	
	void setDealingState() {
		setState(dealingState);
	}
	
	void setWaitingState() {
		setState(waitingState);
	}
}