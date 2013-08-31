package com.blox.setgame.model;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.utils.SetGame;

public class PracticeModeTable extends LearningModeTable {
	private final static float tapWait = 2f;
	private final static int totalDeals = 20;
	private final static float timePerDeal = 10;

	private float lastTap;
	private float time;
	private int score = 0;
	private int deals = 1;
	private boolean waitForContinue;
	
	private void drawScore() {
		Game.renderingShiftY = 60;
		TextDrawer.draw(FontManager.defaultFont, "Score: " + score, infoArea, TextDrawer.AlignSW);
		Game.renderingShiftY = 0;
	}

	private void drawRemainingDeals() {
		TextDrawer.draw(FontManager.defaultFont, "Deals: " + deals + "/" + totalDeals, infoArea, TextDrawer.AlignSW);
	}

	private void drawRemainingTime() {
		TextDrawer.draw(FontManager.defaultFont, "" + (1 + ((int) (timePerDeal - time))), infoArea, TextDrawer.AlignNE);
	}

	private void drawWaitMessage() {
		TextDrawer.draw(FontManager.defaultFont, "Wait: " + String.format("%.1f", tapWait - (time - lastTap)), infoArea, TextDrawer.AlignNW);
	}
	
	private void drawResults() {
		Game.renderingShiftY = 30;
		TextDrawer.draw(FontManager.defaultFont, "Score: " + score);
		Game.renderingShiftY = -30;
		TextDrawer.draw(FontManager.defaultFont, "Touch Screen To Continue");
		Game.renderingShiftY = 0;
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
		}
		else if (timePerDeal - time < 0) {
			SetGame.playSoundTimeUp();
			Game.getVibrator().vibrate(100);
			deal();
		}
	}

	public void continuePlaying() {
		waitForContinue = false;
		score = 0;
		deals = 0;
		time = 0;
		lastTap = -100;
		deal();
	}

	@Override
	protected void deal() {
		super.deal();
		time = 0;
		lastTap = -100;
		deals++;
	}

	@Override
	protected int checkSet() {
		float f = timePerDeal - time;
		int s = super.checkSet();
		score += (int)(s * f);
		return s;
	}

	@Override
	public void cardTapped(Card card) {
		if (time - lastTap < tapWait) {
			lastTap = time;
			SetGame.playSoundWait();
		}
		else {
			lastTap = time;
			super.cardTapped(card);
		}
	}

	@Override
	public void draw() {
		if (waitForContinue) {
			drawResults();
		}
		else {
			drawScore();
			drawRemainingDeals();
			drawRemainingTime();
			if (time - lastTap < tapWait)
				drawWaitMessage();
			super.draw();
		}
	}

	private static final IDrawingInfo infoArea = new IDrawingInfo() {
		private Vector location = new Vector(50, 25);
		
		@Override
		public boolean isFlipY() {
			return false;
		}
		
		@Override
		public boolean isFlipX() {
			return false;
		}
		
		@Override
		public boolean ignoreViewportScaling() {
			return false;
		}
		
		@Override
		public boolean ignoreViewportOffset() {
			return false;
		}
		
		@Override
		public float getWidth() {
			return Game.getVirtualWidth() - 100;
		}
		
		@Override
		public Vector getScale() {
			return noScale;
		}
		
		@Override
		public Rotation getRotation() {
			return noRotation;
		}

		@Override
		public Color getColor() {
			return Color.White;
		}
		
		@Override
		public Vector getLocation() {
			return location;
		}
		
		@Override
		public float getHeight() {
			return Game.getVirtualHeight() - 50;
		}
	};
}