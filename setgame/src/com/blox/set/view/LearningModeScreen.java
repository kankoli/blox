package com.blox.set.view;

import com.blox.set.model.SingleGameTable2;

public class LearningModeScreen extends SetGameScreen {
	private SingleGameTable2 table;

	@Override
	public void init() {
		super.init();
		table = new SingleGameTable2();
	}

	@Override
	public void render() {
		super.render();
		table.draw();
	}
}
