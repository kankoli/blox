package com.blox.setgame.model;

import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Timer;

public class PracticeMode extends TrainingMode {
	private final static float blockDuration = 2f;
	private final static float timePerDeal = 5;
	private final static int totalDeals = 20;

	private final Timer blockTimer;
	private final Timer dealTimer;

	private int deals = 0;
	private int score = 0;

	private IPracticeModeModelListener modeListener;
	private boolean waitForNewGame;

	public void setModeListener(IPracticeModeModelListener modeListener) {
		super.setGameListener(modeListener);
		this.modeListener = modeListener;
	}

	public int getScore() {
		return score;
	}

	public PracticeMode() {
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

	private void onBlockTimerTick() {
		blockTimer.stop();
		notifyUnblocked();
	}

	private void onDealTimerTick() {
		blockTimer.stop();
		dealTimer.stop();
		notifyDealTimeUp();
	}

	private void notifyUnblocked() {
		if (modeListener != null)
			modeListener.onUnblock();
	}

	private void notifyDealTimeUp() {
		if (modeListener != null)
			modeListener.onDealTimeUp();
	}

	private void notifyModeEnd() {
		if (modeListener != null)
			modeListener.onModeEnd();
	}

	private boolean requiresNewDeal() {
		return waitForNewGame || deals < totalDeals;
	}

	private void addScore(int score) {
		this.score += (int) (timePerDeal - dealTimer.getElapsedTime()) * score;
	}

	private void block() {
		blockTimer.start();
	}

	public void dealEnd() {
		dealTimer.start();
	}

	public void startMode() {
		if (!waitForNewGame)
			return;
		score = 0;
		deals = 0;
		waitForNewGame = false;
	}

	public void endMode() {
		deactivateCards();
		blockTimer.stop();
		dealTimer.stop();
		waitForNewGame = true;
		cards.empty();
	}

	@Override
	public void exitMode() {
		super.exitMode();
		blockTimer.stop();
		dealTimer.stop();
		score = 0;
		deals = 0;
		waitForNewGame = false;
	}

	@Override
	public void checkSet(Card selectedCard) {
		int score = cards.checkScore(selectedCard);
		if (score > 0) {
			addScore(score);
			notifySetFound();
		}
		else {
			block();
			notifyInvalidSetSelected();
		}
	}
	
	@Override
	public void deal() {
		if (!requiresNewDeal()) {
			notifyModeEnd();
			return;
		}
		
		deals++;
		blockTimer.stop();
		dealTimer.stop();
		super.deal();
	}

	@Override
	public void draw() {
		if (waitForNewGame) {
			drawResults();
			return;
		}

		super.draw();

		drawScore();
		drawRemainingDeals();
		drawRemainingTime();
		if (blockTimer.isRunning())
			drawWaitMessage();
	}

	private void drawRemainingDeals() {
		PracticeModeInfo.draw("Deals: " + deals + "/" + totalDeals, TextDrawer.AlignSW, 0);
	}

	private void drawRemainingTime() {
		int t = (int) Math.min(timePerDeal, (1 + timePerDeal - dealTimer.getElapsedTime()));
		PracticeModeInfo.draw("" + t, TextDrawer.AlignNE, 0);
	}

	private void drawScore() {
		PracticeModeInfo.draw("Score: " + score, TextDrawer.AlignSW, 60);
	}

	private void drawWaitMessage() {

		PracticeModeInfo.draw("Wait: " + String.format("%.1f", blockDuration - blockTimer.getElapsedTime()), TextDrawer.AlignNW, 0);
	}

	private void drawResults() {
		PracticeModeInfo.draw("Score: " + score, TextDrawer.AlignCentered, 50);
		PracticeModeInfo.draw("Touch Screen", TextDrawer.AlignCentered, -50);
		PracticeModeInfo.draw("To Continue", TextDrawer.AlignCentered, -100);
	}
}