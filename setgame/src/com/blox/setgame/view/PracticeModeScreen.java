package com.blox.setgame.view;

import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.setgame.controller.PracticeModeController;

public class PracticeModeScreen extends SetGameScreen {
	private PracticeModeController controller;

	@Override
	public void init() {
		super.init();
		controller = new PracticeModeController(this);
	}
	
	@Override
	public void update() {
		super.update();
		controller.execute();
	}
	
	@Override
	public void activated() {
		super.activated();
		FontManager.defaultFont.setColor(Color.White);
		controller.activated();
	}
	
	@Override
	public void deactivated() {
		super.deactivated();
		FontManager.defaultFont.setColor(Color.Black);
		controller.deactivated();
	}
	
	@Override
	public boolean tap(float x, float y, int count, int button) {
		controller.tap(x, y, count, button);
		return super.tap(x, y, count, button);
	}
}
