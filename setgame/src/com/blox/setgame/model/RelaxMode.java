package com.blox.setgame.model;

import com.blox.framework.v0.util.TextDrawer;

public class RelaxMode extends SetGameMode {
	private FullGameCards cards;
	private int selectedCardCount;
	private GameInfo info;
	private Hint hint;

	public RelaxMode() {
		cards = new FullGameCards();
		dealer = new FullGameCardDealer(cards);
		info = new GameInfo(7, 25);
		hint = new Hint();
	}

	private FullGameCardDealer getDealer() {
		return (FullGameCardDealer) dealer;
	}

	private void drawCards() {
		cards.draw();
	}

	private void drawRemainingCards() {
		info.draw("Cards: " + getDealer().getIndex() + "/" + Card.CardsInDeck, TextDrawer.AlignSE, 40);
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

	private void updateHints() {
		cards.updateHint(hint);
	}
	
	public void exitMode() {
		deactivateCards();
		cards.empty();
		hint.listenInput(false);
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
		hint.listenInput(true);
	}

	@Override
	public void draw() {
		hint.draw();
		drawCards();
		drawRemainingCards();
	}
}