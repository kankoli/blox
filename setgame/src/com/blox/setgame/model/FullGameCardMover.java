package com.blox.setgame.model;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.impl.TargetMover;

public class FullGameCardMover extends TargetMover {
	FullGameCardMover(float duration) {
		super(duration);
	}

	private final static float maxScale = 0.2f;
	private float totalDist;

	@Override
	public void start() {
		super.start();
		totalDist = end.dist(start);
	}

	@Override
	public void move(IMovable movable) {
		updateScale((Card) movable);
		super.move(movable);
	}

	private void updateScale(Card card) {
		float progress = (float) Math.sqrt(distToTarget) / totalDist;

		float s = 1;
		if (progress < 0.5f)
			s = 1f + 2 * progress * maxScale;
		else
			s = 1f + maxScale - (2 * progress - 1) * maxScale;

		card.getScale().set(s, s);
	}
}
