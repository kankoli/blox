package com.blox.setgame.model;

import com.blox.framework.v0.util.Game;

class CardFader {
	private final static int Passive = 0;
	private final static int FadeIn = 1;
	private final static int FadeOut = 2;

	private int state;
	private float duration;
	private float elapsed;

	private ICardFadingListener listener;

	CardFader(float duration) {
		this.duration = duration;
	}

	float getDuration() {
		return duration;
	}

	void setDuration(float duration) {
		this.duration = duration;
	}

	void beginFadeOut(ICardFadingListener listener) {
		elapsed = 0;
		state = FadeOut;
		this.listener = listener;
	}

	void beginFadeIn(ICardFadingListener listener) {
		elapsed = 0;
		state = FadeIn;
		this.listener = listener;
	}

	boolean update(Card card) {
		if (state == Passive)
			return false;

		elapsed += Game.getDeltaTime();
		float progress = elapsed / duration;

		if (progress >= 1) {
			if (listener != null)
				listener.onCardFadingEnd(card, state == FadeIn);
			state = Passive;
			return false;
		}

		if (state == FadeIn)
			card.getColor().a = progress;
		else
			card.getColor().a = 1 - progress;

		return true;
	}
}
