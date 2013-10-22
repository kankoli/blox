package com.turpgames.ichigu.model.singlegame;

import com.turpgames.ichigu.model.Card;
import com.turpgames.ichigu.model.IchiguMode;
import com.turpgames.ichigu.model.PointsInfo;
import com.turpgames.ichigu.utils.R;

public abstract class SingleGameMode extends IchiguMode {
	protected SingleGameCards cards;
	protected PointsInfo pointsInfo;
	
	protected SingleGameMode() {
		cards = new SingleGameCards();
		dealer = new SingleGameCardDealer(cards);
		pointsInfo = new PointsInfo();
	}

	public SingleGameCards getCards() {
		return cards;
	}

	public void onCardSelected(Card selectedCard) {
		selectedCard.deselect();
		int score = cards.checkScore(selectedCard);
		if (score > 0) {
			notifyIchiguFound();
			pointsInfo.show(cards.getIchiguCards(selectedCard));
		} else
			wrongCardSelected();
	}

	protected void wrongCardSelected() {
		notifyInvalidIchiguSelected();
	}

	public void activateCards() {
		for (int i = 0; i < SingleGameCards.CardToSelectCount; i++)
			cards.getCardsToSelect(i).activate(modeListener);
	}

	public void deactivateCards() {
		for (int i = 0; i < SingleGameCards.CardToSelectCount; i++) {
			if (cards.getCardsToSelect(i) != null)
				cards.getCardsToSelect(i).deactivate();
		}
	}

	public void updateCardLocations() {
		for (int i = 0; i < cards.getLength(); i++)
			cards.get(i).getLocation().set(R.learningModeScreen.layout.positions[i]);
	}

	public boolean exitMode() {
		((SingleGameCardDealer) dealer).abortDeal();
		deactivateCards();
		cards.empty();
		return true;
	}
}