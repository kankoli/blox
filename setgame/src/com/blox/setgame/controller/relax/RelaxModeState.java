package com.blox.setgame.controller.relax;

import com.blox.setgame.controller.SetGameState;
import com.blox.setgame.model.RelaxMode;
import com.blox.setgame.view.RelaxModeScreen;

public abstract class RelaxModeState extends SetGameState implements IRelaxModeActionListener {
	final RelaxMode model;
	final RelaxModeScreen view;
	final RelaxModeController controller;

	public RelaxModeState(RelaxModeController controller) {
		this.controller = controller;
		this.model = controller.model;
		this.view = controller.view;
	}

	@Override
	public void onModeEnd() {
		controller.setEndState();
	}

	@Override
	public boolean onScreenTapped() {
		return false;
	}
}
