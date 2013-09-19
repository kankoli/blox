package com.blox.setgame.model;

import java.util.HashMap;
import java.util.Map;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.effects.IEffectEndListener;
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
			notifyDealEnd();
		}
		else {
			removeSetCards();
		}
	}

	public int getIndex() {
		return index;
	}

	private void initCards() {
		for (int i = 0; i < FullGameCards.TotalCardsOnTable; i++) {
			cards.setCard(i, deck[i]);
			deck[i].getLocation().set(cardLocations.get(i));
			if (i < FullGameCards.ActiveCardCount)
				deck[i].open();
			else
				deck[i].close();
		}

		index = FullGameCards.TotalCardsOnTable;
	}

	private void removeSetCards() {
		cardsToFadeOut = FullGameCards.SetCardCount;
		for (int i = 0; i < FullGameCards.SetCardCount; i++)
			cards.getSelectedCard(i).fadeOut(fadeOutListener);
	}

	private void onFadeOutComplete() {
		startMovingExtraCards();
		if (cardsToMove == 0)
			onCardsMoveEnd();
	}

	private void startMovingExtraCards() {
		cardsToMove = 0;
		int extraCardIndex = 0;
		for (int i = FullGameCards.ActiveCardCount - 1; i >= 0; i--) {
			if (cards.isActiveCardEmpty(i) || !cards.getActiveCard(i).isSelected())
				continue;

			extraCardIndex = findNextAvailableExtraCardIndex(extraCardIndex);
			if (extraCardIndex < 0)
				break;

			startMovingExtraCard(extraCardIndex, i);

			extraCardIndex++;
			cardsToMove++;
		}
	}

	private void startMovingExtraCard(int extraCardIndex, int targetActiveCardIndex) {
		Card extraCard = cards.getExtraCard(extraCardIndex);
		extraCard.open();

		movers[cardsToMove].updateRoute(extraCard.getLocation(), cards.getActiveCard(targetActiveCardIndex).getLocation());
		movers[cardsToMove].start();

		extraCard.setMover(movers[cardsToMove]);
		notifyStartMoving(extraCard);
	}

	private void onCardsMoveEnd() {
		cards.emptySelectedCards();
		if (index < deck.length)
			dealExtraCards();
		notifyDealEnd();
	}

	private int findNextAvailableExtraCardIndex(int searchFrom) {
		for (int i = searchFrom; i < FullGameCards.ExtraCardCount; i++) {
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

	private void onCardFadeOutComplete() {
		cardsToFadeOut--;
		if (cardsToFadeOut == 0)
			onFadeOutComplete();
	}

	private void onCardMoveEnd(Card card) {
		notifyStopMoving(card);

		for (int i = 0; i < FullGameCards.ActiveCardCount; i++) {
			if (!cards.isActiveCardEmpty(i) && cards.getActiveCard(i).isSelected()) {
				cards.setActiveCard(i, card);
				break;
			}
		}

		for (int i = 0; i < FullGameCards.ExtraCardCount; i++) {
			if (card == cards.getExtraCard(i)) {
				cards.setExtraCard(i, null);
				break;
			}
		}

		cardsToMove--;
		if (cardsToMove == 0)
			onCardsMoveEnd();
	}

	public void reset() {
		super.reset();
		index = 0;
	}

	private final IEffectEndListener fadeOutListener = new IEffectEndListener() {
		@Override
		public boolean onEffectEnd(Object obj) {
			onCardFadeOutComplete();
			return true;
		}
	};

	private final TargetMover.IMoveEndListener moveEndListener = new TargetMover.IMoveEndListener() {
		@Override
		public boolean moveEnd(TargetMover mover, IMovable movable) {
			onCardMoveEnd((Card) movable);
			return false;
		}
	};
}
