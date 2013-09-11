package com.blox.setgame.utils;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.impl.TargetMover;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.model.Card;
import com.blox.setgame.model.CardAttributes;
import com.blox.setgame.model.TrainingCards;

public class TrainingCardDealer extends CardDealer {
	// region

	private final static float moveDuration = 0.25f;

	private final static Vector target = new Vector((Game.getVirtualWidth() - Card.Width) / 2, (Game.getVirtualHeight() - Card.Height) / 2);

	private final static Vector[][] routes = new Vector[][] {
			new Vector[] { R.learningModeScreen.layout.cardOnTable1Pos, target },
			new Vector[] { R.learningModeScreen.layout.cardOnTable2Pos, target },
			new Vector[] { R.learningModeScreen.layout.cardToSelect1Pos, target },
			new Vector[] { R.learningModeScreen.layout.cardToSelect2Pos, target },
			new Vector[] { R.learningModeScreen.layout.cardToSelect3Pos, target }
	};

	// endregion

	private final IMovable[] movables = new IMovable[5];

	private final TargetMover[] movers = new TargetMover[] {
			new TargetMover(moveDuration),
			new TargetMover(moveDuration),
			new TargetMover(moveDuration),
			new TargetMover(moveDuration),
			new TargetMover(moveDuration)
	};

	protected final Card[] deck;
	protected final TrainingCards cards;
	private int moverCount;

	private final Integer[] cardsToSelectIndices = new Integer[TrainingCards.CardToSelectCount];

	public TrainingCardDealer(TrainingCards cards) {
		this.deck = Card.newDeck();
		this.cards = cards;
	}

	@Override
	public void deal() {
		if (!cards.isEmpty())
			dealAndMoveNewCards();
		else
			beginMoveOldCards();
	}

	private void dealAndMoveNewCards() {
		dealNewCards();
		beginMoveNewCards();
	}

	private void beginMoveNewCards() {
		beginMove(newCardsMoveEndListener);
	}

	private void beginMoveOldCards() {
		beginMove(oldCardsMoveEndListener);
	}

	private void beginMove(TargetMover.IMoveEndListener endListener) {
		int start = 0;
		int end = 1;

		if (endListener == newCardsMoveEndListener) {
			start = 1;
			end = 0;

			for (int i = 0; i < cards.getLength(); i++)
				movables[i] = cards.get(i);
		}

		for (int i = 0; i < movers.length; i++) {
			movers[i].updateRoute(routes[i][start], routes[i][end]);
			movers[i].setMoveEndListener(endListener);
			movers[i].start();
			movables[i].setMover(movers[i]);
			notifyStartMoving((Card) movables[i]);
		}

		moverCount = movers.length;
	}

	protected void dealNewCards() {
		int c1 = Utils.randInt(deck.length);
		int c2 = Utils.randInt(deck.length);

		while (c1 == c2)
			c2 = Utils.randInt(deck.length);

		cards.setCardsOnTable(deck[c1], deck[c2]);

		int c3 = getCompletingCardIndex(deck[c1].getAttributes(), deck[c2].getAttributes());
		int c4 = Utils.randInt(deck.length);
		int c5 = Utils.randInt(deck.length);

		while (c4 == c1 || c4 == c2 || c4 == c3)
			c4 = Utils.randInt(deck.length);
		while (c5 == c1 || c5 == c2 || c5 == c3 || c5 == c4)
			c5 = Utils.randInt(deck.length);

		setCards(c1, c2, c3, c4, c5);
	}

	protected void setCards(int c1, int c2, int c3, int c4, int c5) {
		// TODO: buraya az daha akýllý bi algoritma uydurulabilir
		cardsToSelectIndices[0] = c3;
		cardsToSelectIndices[1] = c4;
		cardsToSelectIndices[2] = c5;

		Utils.shuffle(cardsToSelectIndices);

		c3 = cardsToSelectIndices[0];
		c4 = cardsToSelectIndices[1];
		c5 = cardsToSelectIndices[2];

		cards.setCardsToSelect(deck[c3], deck[c4], deck[c5]);

		deck[c1].getLocation().set(routes[0][1]);
		deck[c2].getLocation().set(routes[1][1]);
		deck[c3].getLocation().set(routes[2][1]);
		deck[c4].getLocation().set(routes[3][1]);
		deck[c5].getLocation().set(routes[4][1]);
	}

	protected int getCompletingCardIndex(CardAttributes a1, CardAttributes a2) {
		CardAttributes a = CardAttributes.getCompletingCardAttributes(a1, a2);
		return findCardIndex(a.getColor(), a.getShape(), a.getCount(), a.getPattern());
	}

	protected int findCardIndex(int color, int shape, int count, int pattern) {
		for (int i = 0; i < deck.length; i++) {
			if (deck[i].getAttributes().equals(color, shape, count, pattern))
				return i;
		}
		return -1;
	}

	private void onOldCardsMoveEnd(IMovable movable) {
		notifyStopMoving((Card) movable);
		if (--moverCount == 0)
			dealAndMoveNewCards();
	}

	private void onNewCardsMoveEnd(IMovable movable) {
		notifyStopMoving((Card) movable);
		if (--moverCount == 0)
			notifyDealEnd();
	}

	private final TargetMover.IMoveEndListener oldCardsMoveEndListener = new TargetMover.IMoveEndListener() {
		@Override
		public boolean moveEnd(TargetMover mover, IMovable movable) {
			onOldCardsMoveEnd(movable);
			return true;
		}
	};

	private final TargetMover.IMoveEndListener newCardsMoveEndListener = new TargetMover.IMoveEndListener() {
		@Override
		public boolean moveEnd(TargetMover mover, IMovable movable) {
			onNewCardsMoveEnd(movable);
			return true;
		}
	};
}