package com.blox.setgame.controller.learning;

import com.blox.setgame.model.Card;
import com.blox.setgame.model.TrainingCards;
import com.blox.setgame.utils.SetGameUtils;

public class LearningModeWaitingState extends LearningModeState {
	public LearningModeWaitingState(LearningModeController controller) {
		super(controller);
	}
	
	@Override
	public void activated() {
		model.activateCardsOnTable(controller);
	}
	
	@Override
	public void deactivated() {
		model.deactivateCards();
	}

	@Override
	public void cardTapped(Card card) {
		card.deselect();
		TrainingCards cards = model.getCards();
		int score = SetGameUtils.checkSet(cards.getCardOnTable0(), cards.getCardOnTable1(), card);
		if (score > 0)
			controller.setDealingState();
	}
}