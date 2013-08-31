package com.blox.setgame.view;

import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.set.model.PracticeModeTable;

public class PracticeModeScreen extends SetGameScreen {
	private PracticeModeTable table;

	@Override
	public void init() {
		super.init();
		table = new PracticeModeTable();
	}
	
	@Override
	public void update() {
		super.update();
		table.update();
	}
	
	@Override
	public void activated() {
		super.activated();
		FontManager.defaultFont.setColor(Color.White);
	}
	
	@Override
	public void deactivated() {
		super.deactivated();
		FontManager.defaultFont.setColor(Color.Black);
	}
	
	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (table.isWaitingForContinue())
			table.continuePlaying();
		return super.tap(x, y, count, button);
	}

	@Override
	public void render() {
		super.render();
		table.draw();
	}
}
