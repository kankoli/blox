package com.blox.setgame.model;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Timer;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.controller.practice.IPracticeModeEventListener;
import com.blox.setgame.utils.SetGameResources;

public class PracticeGame extends TrainingGame {
	private static final IDrawingInfo infoArea = new IDrawingInfo() {
		private final Vector location = new Vector(50, 25);
		private final float width = Game.getVirtualWidth() - 100;
		private final float height = Game.getVirtualHeight() - 50;
		private final Color color = Color.white(); 

		@Override
		public Color getColor() {
			return color;
		}

		@Override
		public float getHeight() {
			return height;
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
			return width;
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

	private final static float blockDuration = 2f;
	private final static float timePerDeal = 10;
	private final static int totalDeals = 20;

	private final Timer blockTimer;
	private final Timer dealTimer;

	private int deals = 0;
	private int score = 0;

	private IPracticeModeEventListener listener;
	private boolean waitForNewGame;

	public void setEventListener(IPracticeModeEventListener listener) {
		this.listener = listener;
	}

	public int getScore() {
		return score;
	}

	public PracticeGame() {
		blockTimer = new Timer();
		dealTimer = new Timer();

		blockTimer.setInterval(blockDuration);
		dealTimer.setInterval(timePerDeal);

		blockTimer.setTickListener(new Timer.ITimerTickListener() {
			@Override
			public void timerTick(Timer timer) {
				onBlockTimerTick();
			}
		});

		dealTimer.setTickListener(new Timer.ITimerTickListener() {
			@Override
			public void timerTick(Timer timer) {
				onDealTimerTick();
			}
		});
	}

	private void notifyUnblocked() {
		if (listener != null)
			listener.unblocked();
	}

	private void notifyDealTimeUp() {
		if (listener != null)
			listener.dealTimeUp();
	}

	private void onBlockTimerTick() {
		blockTimer.stop();
		notifyUnblocked();
	}

	private void onDealTimerTick() {
		blockTimer.stop();
		dealTimer.stop();
		SetGameResources.playSoundTimeUp();
		Game.vibrate(100);
		notifyDealTimeUp();
	}

	public boolean requiresNewDeal() {
		return waitForNewGame || deals < totalDeals;
	}

	public void addScore(int score) {
		this.score += (int) (timePerDeal - dealTimer.getElapsedTime()) * score;
	}

	public void block() {
		blockTimer.start();
	}

	public void dealStart() {
		blockTimer.stop();
		dealTimer.stop();		
	}
	
	public void dealEnd() {
		dealTimer.start();
		deals++;
	}

	public void exitGame() {
		blockTimer.stop();
		dealTimer.stop();
		score = 0;
		deals = 0;
		cards.empty();
	}
	
	public void endGame() {
		blockTimer.stop();
		dealTimer.stop();
		waitForNewGame = true;
		cards.empty();
	}

	public void startGame() {
		if (!waitForNewGame)
			return;
		score = 0;
		deals = 0;
		waitForNewGame = false;
	}

	@Override
	public void draw() {
		if (waitForNewGame) {
			drawResults();
			return;
		}

		drawScore();
		drawRemainingDeals();
		drawRemainingTime();
		if (blockTimer.isRunning())
			drawWaitMessage();
		super.draw();
	}

	private void drawRemainingDeals() {
		TextDrawer.draw(FontManager.defaultFont, "Deals: " + deals + "/" + totalDeals, infoArea, TextDrawer.AlignSW);
	}

	private void drawRemainingTime() {
		int t = Math.min(10, (int) (1 + timePerDeal - dealTimer.getElapsedTime()));
		TextDrawer.draw(FontManager.defaultFont, "" + t, infoArea, TextDrawer.AlignNE);
	}

	private void drawScore() {
		Game.renderingShiftY = 60;
		TextDrawer.draw(FontManager.defaultFont, "Score: " + score, infoArea, TextDrawer.AlignSW);
		Game.renderingShiftY = 0;
	}

	private void drawWaitMessage() {
		TextDrawer.draw(FontManager.defaultFont, "Wait: " + String.format("%.1f", blockDuration - blockTimer.getElapsedTime()), infoArea, TextDrawer.AlignNW);
	}

	private void drawResults() {
		Game.renderingShiftY = 50;
		TextDrawer.draw(FontManager.defaultFont, "Score: " + score);
		Game.renderingShiftY = -50;
		TextDrawer.draw(FontManager.defaultFont, "Touch Screen");
		Game.renderingShiftY = -100;
		TextDrawer.draw(FontManager.defaultFont, "To Continue");
		Game.renderingShiftY = 0;
	}
}