package com.blox.setgame.model;

import java.util.ArrayList;
import java.util.List;

class FullGameSetInfo {
	private final List<Integer> sets;

	FullGameSetInfo() {
		sets = new ArrayList<Integer>();
	}

	public void update(Card[] cards) {
		sets.clear();

		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				for (int k = j + 1; k < cards.length; k++) {
					if (cards[i] != null && cards[j] != null && cards[k] != null &&
							cards[i].isOpened() && cards[j].isOpened() && cards[k].isOpened() &&
							Card.isSet(cards[i], cards[j], cards[k])) {
						sets.add(i);
						sets.add(j);
						sets.add(k);
					}
				}
			}
		}
	}

	public int getSetCardIndex(int setIndex, int cardIndex) {
		return sets.get(setIndex * 3 + cardIndex);
	}

	public int getSetCount() {
		return sets.size() / 3;
	}
}
