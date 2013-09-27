package com.blox.setgame.model;

import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Timer;
import com.blox.setgame.utils.R;
import com.blox.setgame.utils.SetGameResources;

public class RelaxMode extends SetGameModeModel {
	private FullGameCards cards;
	private GameInfo info;
	private FullGameHint hint;
	private SetGameImageButton resetButton;
	private Timer timer;
	private ScreenTouchHandler touchHandler;
	private String modeCompleteTime;
	private int setsFound;
	private int selectedCardCount;

	private final IScreenTouchListener touchListener = new IScreenTouchListener() {
		@Override
		public void onScreenTouched() {
			notifyNewGame();
		}
	};

	public RelaxMode() {
		cards = new FullGameCards();
		dealer = new FullGameCardDealer(cards);
		info = new GameInfo(7, 25);
		touchHandler = new ScreenTouchHandler();
		timer = new Timer();
		timer.setInterval(1);

		hint = new FullGameHint();
		hint.getLocation().set(Game.getVirtualWidth() - 58, 50);
		hint.setSlideY(Game.getVirtualHeight() - 100);

		resetButton = new SetGameImageButton();
		resetButton.getLocation().set(10, 50);
		resetButton.setTexture(R.game.textures.refresh);
		resetButton.setListener(new ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				endMode();
				startMode();
				deal();
			}
		});

	}

	public FullGameCards getCards() {
		return cards;
	}

	private IRelaxModeListener getModeListener() {
		return (IRelaxModeListener) super.modelListener;
	}

	private FullGameCardDealer getDealer() {
		return (FullGameCardDealer) dealer;
	}

	private String getTimeString() {
		int elapsed = (int) timer.getTotalElapsedTime();
		int min = elapsed / 60;
		int sec = elapsed % 60;

		return (min < 10 ? ("0" + min) : ("" + min)) +
				":" +
				(sec < 10 ? ("0" + sec) : ("" + sec));
	}

	private void notifyModeEnd() {
		touchHandler.activate(touchListener);
		if (getModeListener() != null)
			getModeListener().onModeEnd();
	}

	private void notifyNewGame() {
		touchHandler.deactivate();
		if (getModeListener() != null)
			getModeListener().onNewGame();
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
			else
				notifyInvalidSetSelected();
			selectedCardCount = 0;
		}
	}

	public void activateCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			if (!cards.isEmpty(i)) {
				cards.getCard(i).activate(modelListener);
			}
		}
		updateHints();
	}

	public void deactivateCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			if (!cards.isEmpty(i)) {
				cards.getCard(i).deactivate();
			}
		}
	}

	public void deselectCards() {
		cards.deselectCards();
	}

	private void openExtraCards() {
		for (int i = 0; i < FullGameCards.ExtraCardCount; i++)
			cards.getExtraCard(i).open();
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
			else
				notifyModeEnd();
		}
	}

	public void startMode() {
		cards.empty();
		dealer.reset();
		touchHandler.deactivate();
		setsFound = 0;
		selectedCardCount = 0;
		timer.start();
		hint.activate();
		resetButton.listenInput(true);
	}

	public void endMode() {
		SetGameResources.playSoundTimeUp();
		modeCompleteTime = getTimeString();
		timer.stop();
		cards.empty();
		deactivateCards();
		hint.deactivate();
		resetButton.listenInput(false);
	}

	public void exitMode() {
		deactivateCards();
		cards.empty();
		timer.stop();
		hint.deactivate();
		resetButton.listenInput(false);
	}

	public void drawGame() {
		drawHint();
		drawTime();
		drawCards();
		drawRemainingCards();
		drawResetButton();
	}

	private void drawResetButton() {
		resetButton.draw();
	}

	public void drawResult() {
		info.draw("Congratulations", TextDrawer.AlignCentered, 275);
		info.draw(String.format("You Found %d Set%s!", setsFound, setsFound > 1 ? "s" : ""), TextDrawer.AlignCentered, 200);
		info.draw("Total Time " + modeCompleteTime, TextDrawer.AlignCentered, 50);
		info.draw("Touch Screen", TextDrawer.AlignCentered, -100);
		info.draw("To Continue", TextDrawer.AlignCentered, -175);
	}

	private void drawHint() {
		hint.draw();
	}

	private void drawCards() {
		cards.draw();
	}

	private void drawRemainingCards() {
		info.draw(getDealer().getIndex() + "/" + Card.CardsInDeck, TextDrawer.AlignS, 55);
	}

	private void drawTime() {
		info.draw(getTimeString(), TextDrawer.AlignS, 25);
	}

	public void pause() {
		timer.pause();
		hint.deactivate();
		resetButton.listenInput(false);
	}

	public void resume() {
		timer.start();
		hint.activate();
		resetButton.listenInput(true);
	}
}