package com.blox.setgame.utils;

import com.blox.framework.v0.util.Game;
import com.blox.setgame.model.Card;

public final class SetGameUtils {
	private SetGameUtils() {

	}

	public static int checkSet(Card... cards) {
		int score = Card.getSetScore(cards[0], cards[1], cards[2]);
		if (score > 0) {
			Game.vibrate(50);
			SetGameResources.playSoundSuccess();
		}
		else {
			Game.vibrate(100);
			SetGameResources.playSoundError();
		}
		return score;
	}
}