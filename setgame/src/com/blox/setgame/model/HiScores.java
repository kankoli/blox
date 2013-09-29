package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.impl.Settings;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.setgame.utils.R;

public class HiScores implements IDrawable {
	private GameInfo info;
	
	public HiScores() {
		info = new GameInfo(20, 0);
	}
	
	@Override
	public void draw() {
		int practiceScore = Settings.getInteger(R.settings.hiscores.practice, 0);
		int challengeScore = Settings.getInteger(R.settings.hiscores.challenge, 0);

		info.draw("Practice Mode: " + practiceScore, TextDrawer.AlignCentered, 60);		
		info.draw("Challenge Mode: " + challengeScore, TextDrawer.AlignCentered, -60);
	}	
}