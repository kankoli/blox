package com.blox.setgame.view;

import com.blox.setgame.model.HiScores;

public class ScoreBoardScreen extends SetGameScreen {
	private HiScores hiScores;

	@Override
	protected String getTitle() {
		return "Hi Scores";
	}

	@Override
	public void activated() {
		super.activated();
		hiScores.activate();
	}

	@Override
	public void deactivated() {
		super.deactivated();
		hiScores.deactivate();
	}

	@Override
	public void init() {
		super.init();
		hiScores = new HiScores();
		registerDrawable(hiScores, 10);
	}
}
