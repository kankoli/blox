package com.blox.set.view;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.util.Game;
import com.blox.set.controller.ChallengeModeController;

public class ChallengeModeScreen extends SetGameScreen {
	private ChallengeModeController controller;
	
	@Override
	public void init() {
		super.init();
		controller = new ChallengeModeController(this);
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
	
	@Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
    		Game.exit();
    	return super.keyDown(keycode);
    }
}
