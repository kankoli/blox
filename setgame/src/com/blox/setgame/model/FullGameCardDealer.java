package com.blox.setgame.model;

import java.util.HashMap;
import java.util.Map;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.impl.TargetMover;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

class FullGameCardDealer extends CardDealer {
	// region static

	private final static int cols = 5;
	// private final static int rows = 3;
	private final static float moveDuration = 0.5f;

	private final static Map<Integer, Vector> cardLocations = new HashMap<Integer, Vector>();

	static {
		float dy = (Game.getVirtualHeight() - Game.getVirtualWidth()) / 2f;
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			int x = i < FullGameCards.ActiveCardCount ? i % (cols - 1) : cols - 1;
			int y = i < FullGameCards.ActiveCardCount ? i / (cols - 1) : i - FullGameCards.ActiveCardCount;

			int dx = i < FullGameCards.ActiveCardCount ? 0 : Card.Space + 1;

			cardLocations.put(i, new Vector(
					x * Card.Width + Card.Space * (x + 1) + dx,
					y * Card.Height + Card.Space * (y + 1) + dy));
		}
	}

	// endregion

	private final FullGameCards cards;
	private int index;

	private int cardsToFadeOut;
	private int cardsToMove;

	private TargetMover[] movers = new TargetMover[] {
			new FullGameCardMover(moveDuration), new FullGameCardMover(moveDuration), new FullGameCardMover(moveDuration)
	};

	FullGameCardDealer(FullGameCards cards) {
		this.cards = cards;
		for (int i = 0; i < movers.length; i++)
			movers[i].setMoveEndListener(moveEndListener);
	}

	@Override
	public void deal() {
		if (index == 0) {
			initCards();
			updateCards();
			notifyDealEnd();
		}
		else {
			removeSetCards();
		}
	}

	private void initCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++)
			cards.setCard(i, deck[i]);
		updateCards();
		index = FullGameCards.TotalCardsOnTable;
	}

	private void updateCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			if (!cards.isEmpty(i))
				cards.getCard(i).getLocation().set(cardLocations.get(i));
		}

		for (int i = 0; i < FullGameCards.ActiveCardCount; i++) {
			if (!cards.isActiveCardEmpty(i))
				cards.getActiveCard(i).open();
		}

		for (int i = 0; i < FullGameCards.ExtraCardCount; i++) {
			if (!cards.isExtraCardEmpty(i))
				cards.getExtraCard(i).close();
		}
	}

	private void removeSetCards() {
		cardsToFadeOut = FullGameCards.SetCardCount;
		for (int i = 0; i < FullGameCards.SetCardCount; i++)
			cards.getSelectedCard(i).fadeOut(fadeOutListener);
	}

	private void onFadeOutComplete() {
		cardsToMove = 0;
		for (int i = 0; i < FullGameCards.ActiveCardCount; i++) {
			if (cards.isActiveCardEmpty(i) || !cards.getActiveCard(i).isSelected())
				continue;
			
			int x = getNextAvailableExtraCardIndex();
			if (x < 0)
				continue;
			
			Card extraCard = cards.getExtraCard(x);
			extraCard.open();

			movers[cardsToMove].updateRoute(extraCard.getLocation(), cards.getActiveCard(i).getLocation());
			movers[cardsToMove].start();

			extraCard.setMover(movers[cardsToMove]);
			notifyStartMoving(extraCard);

			cardsToMove++;

			cards.setActiveCard(i, cards.getExtraCard(x));
			cards.setExtraCard(x, null);
		}
		cards.emptySelectedCards();

		if (cardsToMove == 0)
			onCardsMoveEnd();
	}

	private void onCardsMoveEnd() {
		if (index < deck.length)
			dealExtraCards();
		updateCards();
		notifyDealEnd();
	}

	private int getNextAvailableExtraCardIndex() {
		for (int i = 0; i < FullGameCards.ExtraCardCount; i++) {
			if (!cards.isExtraCardEmpty(i) && !cards.getExtraCard(i).isSelected())
				return i;
		}
		return -1;
	}

	private void dealExtraCards() {
		for (int i = 0; i < FullGameCards.SetCardCount; i++) {
			Card card = deck[index++];
			card.getLocation().set(cardLocations.get(i + FullGameCards.ActiveCardCount));
			card.close();
			cards.setExtraCard(i, card);
		}
	}

	public void reset() {
		super.reset();
		index = 0;
	}

	private final ICardFadingListener fadeOutListener = new ICardFadingListener() {
		@Override
		public void onCardFadingEnd(Card card, boolean fadeIn) {
			cardsToFadeOut--;
			if (cardsToFadeOut == 0)
				onFadeOutComplete();
		}
	};

	private final TargetMover.IMoveEndListener moveEndListener = new TargetMover.IMoveEndListener() {
		@Override
		public boolean moveEnd(TargetMover mover, IMovable movable) {
			cardsToMove--;
			if (cardsToMove == 0)
				onCardsMoveEnd();
			return false;
		}
	};
}
