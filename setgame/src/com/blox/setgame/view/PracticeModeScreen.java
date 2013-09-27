package com.blox.setgame.view;

import com.blox.setgame.controller.practice.PracticeModeController;

public class PracticeModeScreen extends SetGameScreen {
	@Override
	protected String getTitle() {
		return "Practice";
	}
	
	@Override
	public void init() {
		super.init();
		setScreenListener(new PracticeModeController(this));
	}
}
