package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.impl.Settings;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.setgame.utils.R;

public class HiScores implements IDrawable {
	private GameInfo info;
	private SetGameTextButton resetScores;

	public HiScores() {
		info = new GameInfo(20, 0);
		info.setFontScale(R.fontSize.medium);

		resetScores = new SetGameTextButton();
		resetScores.setFont(FontManager.createDefaultFontInstance());
		resetScores.setText("Reset Hi Scores");
		resetScores.listenInput(false);
		resetScores.setListener(new ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				Settings.putInteger(R.settings.hiscores.practice, 0);
				Settings.putInteger(R.settings.hiscores.challenge, 0);
			}
		});
		
		resetScores.getLocation().set(
			(Game.getVirtualWidth() - resetScores.getWidth()) / 2, 200
		);
	}
	
	public void activate() {
		resetScores.listenInput(true);
	}
	
	public void deactivate() {
		resetScores.listenInput(false);
	}

	@Override
	public void draw() {
		int practiceScore = Settings.getInteger(R.settings.hiscores.practice, 0);
		int challengeScore = Settings.getInteger(R.settings.hiscores.challenge, 0);

		info.draw("Practice: " + practiceScore, TextDrawer.AlignCentered, 200);
		info.draw("Challenge: " + challengeScore, TextDrawer.AlignCentered, 80);
		
		resetScores.draw();
	}
}