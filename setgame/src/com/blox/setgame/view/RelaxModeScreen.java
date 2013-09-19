package com.blox.setgame.view;

import com.blox.setgame.controller.relax.RelaxModeController;

public class RelaxModeScreen extends SetGameScreen {
	@Override
	public void init() {
		super.init();
		setScreenListener(new RelaxModeController(this));
	}
	
	private IRelaxModeViewListener getScreenListener() {
		return (IRelaxModeViewListener)screenListener;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return notifyScreenTapped();
	}

	private boolean notifyScreenTapped() {
		if (screenListener != null)
			return getScreenListener().onScreenTapped();
		return false;
	}
}