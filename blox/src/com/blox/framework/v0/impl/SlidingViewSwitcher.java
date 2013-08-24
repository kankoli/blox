package com.blox.framework.v0.impl;

import com.blox.framework.v0.util.Game;

public class SlidingViewSwitcher extends ViewSwitcher {
	public SlidingViewSwitcher(float duration) {
		super(duration);
	}

	@Override
	protected void renderSwitching() {
		float tmp = Game.renderingShiftX;

		float alpha = elapsed / duration;

		Game.renderingShiftX = -alpha * Game.getVirtualWidth();
		oldView.render();

		Game.renderingShiftX = (1 - alpha) * Game.getVirtualWidth();
		newView.render();

		Game.renderingShiftX = tmp;
	}
}
