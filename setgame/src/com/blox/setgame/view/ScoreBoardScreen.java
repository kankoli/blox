package com.blox.setgame.view;

import com.blox.setgame.model.HiScores;

public class ScoreBoardScreen extends SetGameScreen {
	@Override
	protected String getTitle() {
		return "Hi Scores";
	}
	
	@Override
	public void init() {
		super.init();
		registerDrawable(new HiScores(), 10);
	}
}
