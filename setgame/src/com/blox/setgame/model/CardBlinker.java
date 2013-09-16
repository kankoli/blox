package com.blox.setgame.model;

import com.blox.framework.v0.util.Game;

class CardBlinker {
	private float duration;
	private float elapsed;
	private int count;
	private boolean isActive;
	private ICardBlinkListener listener;

	CardBlinker(float duration, int count) {
		super();
		this.duration = duration;
		this.count = count;
	}

	void update(Card card) {
		if (!isActive)
			return;
		
		elapsed += Game.getDeltaTime();
		
		if (elapsed >= duration) {
			notifyBlinkEnd(card);
			card.getColor().a = 1;
			isActive = false;
			return;
		}
		
		card.getColor().a = ((int)(elapsed * count / duration)) % 2 == 0 ? 0 : 1; 
	}

	private void notifyBlinkEnd(Card card) {
		if (listener != null)
			listener.onCardBlinkEnd(card);
		
	}

	void blink(ICardBlinkListener listener) {
		this.listener = listener;
		this.elapsed = 0;
		this.isActive = true;
	}
}