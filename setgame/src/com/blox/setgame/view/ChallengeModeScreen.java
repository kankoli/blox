package com.blox.setgame.view;

import com.blox.setgame.controller.challenge.ChallengeModeController;

public class ChallengeModeScreen extends SetGameScreen {
	@Override
	protected String getTitle() {
		return "Challenge";
	}
	
	@Override
	public void init() {
		super.init();
		setScreenListener(new ChallengeModeController(this));
	}
}
