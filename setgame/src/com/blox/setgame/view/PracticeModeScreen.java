package com.blox.setgame.view;

import com.blox.setgame.controller.practice.PracticeModeController;

public class PracticeModeScreen extends SetGameScreen {
	
	private IPracticeModeViewListener getScreenListener() {
		return (IPracticeModeViewListener)screenListener;
	}

	@Override
	public void init() {
		super.init();
		setScreenListener(new PracticeModeController(this));
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
