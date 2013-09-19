package com.blox.setgame.model;

public class LearningMode extends TrainingMode {
	private LearningModeHint hint;
	private LearningModeTutorial tutorial;

	private boolean isTutorialMode;
	private ILearningModeModelListener listener;

	public LearningMode() {
		hint = new LearningModeHint();
		tutorial = new LearningModeTutorial(new LearningModeTutorial.ILearningModeTutorialListener() {
			@Override
			public void onTutorialEnd() {
				notifyTutorialEnd();
			}
		});
		isTutorialMode = true;
	}
	
	public void setModeListener(ILearningModeModelListener listener) {
		super.setGameListener(listener);
		this.listener = listener;
	}
	
	private void notifyTutorialEnd() {
		if (listener != null)
			listener.onTutorialEnd();
	}

	@Override
	public void activateCardsOnTable() {
		super.activateCardsOnTable();

		Card[] allCards = cards.getAllCards();

		Card card3 = null;
		for (int i = 0; i < TrainingCards.CardToSelectCount; i++) {
			if (Card.isSet(allCards[0], allCards[1], cards.getCardsToSelect(i))) {
				card3 = cards.getCardsToSelect(i);
				break;
			}
		}

		hint.update(allCards[0], allCards[1], card3);
		hint.listenInput(true);
	}

	@Override
	public void deactivateCards() {
		hint.listenInput(false);
		super.deactivateCards();
	}

	@Override
	public void draw() {
		if (isTutorialMode) {
			tutorial.draw();
		}
		else {
			super.draw();
			hint.draw();
		}
	}

	public void endTutorial() {
		tutorial.listenInput(false);		
		isTutorialMode = false;
	}

	public void beginTutorial() {
		hint.listenInput(false);
		tutorial.reset();
		tutorial.listenInput(true);
		isTutorialMode = true;
	}
}
