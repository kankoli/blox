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
	private GameInfo info;

	private IPracticeModeModelListener modeListener;
	private ScreenTouchHandler touchHandler;

	private final ScreenTouchHandler.IScreenTouchListener touchListener = new ScreenTouchHandler.IScreenTouchListener() {
		@Override
		public void onScreenTouched() {
			notifyNewGame();
		}
	};

	public void setModeListener(IPracticeModeModelListener modeListener) {
		super.setGameListener(modeListener);
		this.modeListener = modeListener;
	}

	public int getScore() {
		return score;
	}

	public PracticeMode() {
		info = new GameInfo(50, 25);

		touchHandler = new ScreenTouchHandler();

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
		touchHandler.activate(touchListener);
		if (modeListener != null)
			modeListener.onModeEnd();
	}

	private void notifyNewGame() {
		touchHandler.deactivate();
		if (modeListener != null)
			modeListener.onNewGame();
	}

	private boolean requiresNewDeal() {
		return deals < totalDeals;
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
		score = 0;
		deals = 0;
	}

	public void endMode() {
		deactivateCards();
		blockTimer.stop();
		dealTimer.stop();
		cards.empty();
	}

	@Override
	public void exitMode() {
		super.exitMode();
		blockTimer.stop();
		dealTimer.stop();
		score = 0;
		deals = 0;
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

	public void drawGame() {
		drawCards();
		drawScore();
		drawRemainingDeals();
		drawRemainingTime();
		if (blockTimer.isRunning())
			drawWaitMessage();
	}

	public void drawResults() {
		info.draw("Score: " + score, TextDrawer.AlignCentered, 50);
		info.draw("Touch Screen", TextDrawer.AlignCentered, -50);
		info.draw("To Continue", TextDrawer.AlignCentered, -100);
	}

	private void drawCards() {
		Card[] allCards = cards.getAllCards();
		for (int i = 0; i < allCards.length; i++)
			allCards[i].draw();
	}

	private void drawRemainingDeals() {
		info.draw("Deals: " + deals + "/" + totalDeals, TextDrawer.AlignSW, 0);
	}

	private void drawRemainingTime() {
		int t = (int) Math.min(timePerDeal, (1 + timePerDeal - dealTimer.getElapsedTime()));
		info.draw("" + t, TextDrawer.AlignNE, 0);
	}

	private void drawScore() {
		info.draw("Score: " + score, TextDrawer.AlignSW, 60);
	}

	private void drawWaitMessage() {
		info.draw("Wait: " + String.format("%.1f", blockDuration - blockTimer.getElapsedTime()), TextDrawer.AlignNW, 0);
	}
}