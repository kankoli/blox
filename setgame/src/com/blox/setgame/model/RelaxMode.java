package com.blox.setgame.model;

import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Timer;
import com.blox.setgame.utils.SetGameResources;

public class RelaxMode extends SetGameMode {
	private FullGameCards cards;
	private int selectedCardCount;
	private GameInfo info;
	private FullGameHint hint;
	private Timer timer;
	private IRelaxModeModelListener modeListener;
	private ScreenTouchHandler touchHandler;
	private String modeCompleteTime;
	private int setsFound;

	private final ScreenTouchHandler.IScreenTouchListener touchListener = new ScreenTouchHandler.IScreenTouchListener() {
		@Override
		public void onScreenTouched() {
			notifyNewGame();
		}
	};

	public RelaxMode() {
		cards = new FullGameCards();
		dealer = new FullGameCardDealer(cards);
		info = new GameInfo(7, 25);
		hint = new FullGameHint();
		touchHandler = new ScreenTouchHandler();
		timer = new Timer();
		timer.setInterval(1);
	}

	public void setModeListener(IRelaxModeModelListener modeListener) {
		super.setGameListener(modeListener);
		this.modeListener = modeListener;
	}

	private FullGameCardDealer getDealer() {
		return (FullGameCardDealer) dealer;
	}

	private void updateHints() {
		cards.updateHint(hint);

		boolean thereIsNoSet = hint.getSetCount() == 0;
		boolean extraCardsAreOpened = cards.isExtraCardEmpty(0) || cards.getExtraCard(0).isOpened();
		boolean hasMoreCardsInDeck = getDealer().getIndex() < Card.CardsInDeck;

		if (thereIsNoSet && extraCardsAreOpened) {
			if (hasMoreCardsInDeck) {
				deactivateCards();
				getDealer().dealExtraCards();
				openExtraCards();
				activateCards();
			}
			else {
				notifyModeEnd();
			}
		}
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

	public FullGameCards getCards() {
		return cards;
	}

	public void activateCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			Card card = cards.getCard(i);
			if (card != null) {
				card.activate();
				card.setEventListener(gameListener);
			}
		}
		updateHints();
	}

	public void deactivateCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			Card card = cards.getCard(i);
			if (card != null) {
				card.deactivate();
				card.setEventListener(null);
			}
		}
	}

	public void cardTapped(Card card) {
		if (!card.isOpened()) {
			card.deselect();
			openExtraCards();
			updateHints();
			return;
		}

		if (card.isSelected())
			selectedCardCount++;
		else
			selectedCardCount--;

		if (selectedCardCount == FullGameCards.SetCardCount) {
			int score = cards.getScore();
			if (score > 0) {
				setsFound++;
				notifySetFound();
			}
			else {
				notifyInvalidSetSelected();
			}
			selectedCardCount = 0;
		}
	}

	private void openExtraCards() {
		for (int i = 0; i < FullGameCards.ExtraCardCount; i++)
			cards.getExtraCard(i).open();
	}

	public void deselectCards() {
		cards.deselectCards();
	}

	public void startMode() {
		cards.empty();
		dealer.reset();
		hint.activate();
		timer.start();
		touchHandler.deactivate();
		setsFound = 0;
		selectedCardCount = 0;
	}

	public void endMode() {
		SetGameResources.playSoundTimeUp();
		modeCompleteTime = getTimeString();
		timer.stop();
		cards.empty();
		deactivateCards();
		hint.deactivate();
	}

	public void exitMode() {
		deactivateCards();
		cards.empty();
		timer.stop();
		hint.deactivate();
	}

	private String getTimeString() {
		int elapsed = (int) timer.getTotalElapsedTime();
		int min = elapsed / 60;
		int sec = elapsed % 60;

		return (min < 10 ? ("0" + min) : ("" + min)) +
				":" +
				(sec < 10 ? ("0" + sec) : ("" + sec));
	}

	public void drawGame() {
		drawHint();
		drawTime();
		drawCards();
		drawRemainingCards();
	}

	public void drawResult() {
		info.draw("Congratulations", TextDrawer.AlignCentered, 275);
		info.draw(String.format("You Found %d Set%s!", setsFound, setsFound > 1 ? "s" : ""), TextDrawer.AlignCentered, 200);
		info.draw("Total Time " + modeCompleteTime, TextDrawer.AlignCentered, 50);
		info.draw("Touch Screen", TextDrawer.AlignCentered, -100);
		info.draw("To Continue", TextDrawer.AlignCentered, -175);
	}

	private void drawCards() {
		cards.draw();
	}

	private void drawRemainingCards() {
		info.draw("Cards: " + getDealer().getIndex() + "/" + Card.CardsInDeck, TextDrawer.AlignSE, 40);
	}

	private void drawHint() {
		hint.draw();
	}

	private void drawTime() {
		info.draw(getTimeString(), TextDrawer.AlignNW, 0);
	}
}