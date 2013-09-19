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
	private boolean modeEnd;
	private String modeCompleteTime;

	public RelaxMode() {
		cards = new FullGameCards();
		dealer = new FullGameCardDealer(cards);
		info = new GameInfo(7, 25);
		hint = new FullGameHint();
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

		if (hint.getSetCount() == 0 &&
				((!cards.isExtraCardEmpty(0) && cards.getExtraCard(0).isOpened()) || cards.isExtraCardEmpty(0))) {
			notifyModeEnd();
		}
	}

	private void notifyModeEnd() {
		if (modeListener != null)
			modeListener.onModeEnd();
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
			for (int i = 0; i < FullGameCards.ExtraCardCount; i++)
				cards.getExtraCard(i).open();
			updateHints();
			return;
		}

		if (card.isSelected())
			selectedCardCount++;
		else
			selectedCardCount--;

		if (selectedCardCount == FullGameCards.SetCardCount) {
			int score = cards.getScore();
			if (score > 0)
				notifySetFound();
			else
				notifyInvalidSetSelected();
			selectedCardCount = 0;
		}
	}

	public void deselectCards() {
		cards.deselectCards();
	}

	public void startMode() {
		cards.empty();
		dealer.reset();
		hint.activate();
		timer.start();
		modeEnd = false;
		selectedCardCount = 0;
	}

	public void endMode() {
		SetGameResources.playSoundTimeUp();
		modeCompleteTime = getTimeString();
		timer.stop();
		cards.empty();
		deactivateCards();
		hint.deactivate();
		modeEnd = true;
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

	@Override
	public void draw() {
		if (modeEnd) {
			drawResult();
		}
		else {
			drawHint();
			drawTime();
			drawCards();
			drawRemainingCards();
		}
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

	private void drawResult() {
		info.draw("Congratulations", TextDrawer.AlignCentered, 275);
		info.draw("You Found All Sets!", TextDrawer.AlignCentered, 200);
		info.draw("Total Time " + modeCompleteTime, TextDrawer.AlignCentered, 50);
		info.draw("Touch Screen", TextDrawer.AlignCentered, -100);
		info.draw("To Continue", TextDrawer.AlignCentered, -175);
	}
}