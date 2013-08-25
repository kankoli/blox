package com.blox.set.view;

import com.blox.set.controller.RelaxModeController;

public class RelaxModeScreen extends SetGameScreen {
	private RelaxModeController controller;
	
	@Override
	public void init() {
		super.init();
		controller = new RelaxModeController(this);
	}
	
	@Override
	public void render() {
		draw();
	}
	
	@Override
	public void update() {
		controller.work();
		super.update();
	}
}
