package com.blox.setgame.view;

import com.blox.set.model.LearningModeTable;

public class LearningModeScreen extends SetGameScreen {
	private LearningModeTable table;

	@Override
	public void init() {
		super.init();
		table = new LearningModeTable();
	}

	@Override
	public void render() {
		super.render();
		table.draw();
	}
}
