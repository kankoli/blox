package com.blox.setgame.model;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.controller.SetGameController;

public class PracticeModeTable extends LearningModeTable {
	private static final IDrawingInfo infoArea = new IDrawingInfo() {
		private Vector location = new Vector(50, 25);

		@Override
		public Color getColor() {
			return Color.White;
		}

		@Override
		public float getHeight() {
			return Game.getVirtualHeight() - 50;
		}

		@Override
		public Vector getLocation() {
			return location;
		}

		@Override
		public Rotation getRotation() {
			return noRotation;
		}

		@Override
		public Vector getScale() {
			return noScale;
		}

		@Override
		public float getWidth() {
			return Game.getVirtualWidth() - 100;
		}

		@Override
		public boolean ignoreViewportOffset() {
			return false;
		}

		@Override
		public boolean ignoreViewportScaling() {
			return false;
		}

		@Override
		public boolean isFlipX() {
			return false;
		}

		@Override
		public boolean isFlipY() {
			return false;
		}
	};
	private final static float tapWait = 2f;
	private final static float timePerDeal = 10;

	private final static int totalDeals = 20;
	private int deals = 1;
	private float lastTap;
	private int score = 0;
	private float time;

	private boolean waitForContinue;

	@Override
	public void cardTapped(Card card) {
		if (time - lastTap < tapWait) {
			lastTap = time;
			SetGameController.playSoundWait();
		} else {
			lastTap = time;
			super.cardTapped(card);
		}
	}

	@Override
	protected int checkSet() {
		float f = timePerDeal - time;
		int s = super.checkSet();
		score += (int) (s * f);
		return s;
	}

	public void continuePlaying() {
		score = 0;
		deals = 0;
		time = 0;
		lastTap = -100;
		deal();
	}

	@Override
	public void deal() {
		super.deal();
		time = 0;
		lastTap = -100;
		deals++;
	}

	@Override
	public void draw() {
		if (waitForContinue) {
			drawResults();
		} else {
			drawScore();
			drawRemainingDeals();
			drawRemainingTime();
			if (time - lastTap < tapWait)
				drawWaitMessage();
			super.draw();
		}
	}

	private void drawRemainingDeals() {
		TextDrawer.draw(FontManager.defaultFont, "Deals: " + deals + "/" + totalDeals, infoArea, TextDrawer.AlignSW);
	}

	private void drawRemainingTime() {
		TextDrawer.draw(FontManager.defaultFont, "" + (1 + ((int) (timePerDeal - time))), infoArea, TextDrawer.AlignNE);
	}

	private void drawResults() {
		Game.renderingShiftY = 30;
		TextDrawer.draw(FontManager.defaultFont, "Score: " + score);
		Game.renderingShiftY = -30;
		TextDrawer.draw(FontManager.defaultFont, "Touch Screen To Continue");
		Game.renderingShiftY = 0;
	}

	private void drawScore() {
		Game.renderingShiftY = 60;
		TextDrawer.draw(FontManager.defaultFont, "Score: " + score, infoArea, TextDrawer.AlignSW);
		Game.renderingShiftY = 0;
	}

	private void drawWaitMessage() {
		TextDrawer.draw(FontManager.defaultFont, "Wait: " + String.format("%.1f", tapWait - (time - lastTap)), infoArea, TextDrawer.AlignNW);
	}

	public boolean isWaitingForContinue() {
		return waitForContinue;
	}

	public void update() {
		time += Game.getDeltaTime();
		if (deals > totalDeals) {
			if (!waitForContinue) {
				Game.getVibrator().vibrate(100);
			}
			waitForContinue = true;
			deactivateCards();
		} else if (timePerDeal - time < 0) {
			SetGameController.playSoundTimeUp();
			Game.getVibrator().vibrate(100);
			deal();
		}
	}
}