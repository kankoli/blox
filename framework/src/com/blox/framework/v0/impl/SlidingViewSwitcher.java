package com.blox.framework.v0.impl;

import com.blox.framework.v0.util.Game;

public class SlidingViewSwitcher extends ViewSwitcher {
	public SlidingViewSwitcher(float duration) {
		super(duration);
	}

	@Override
	protected void renderSwitching(boolean back) {
		float tmp = Game.renderingShiftX;

		float dx = elapsed / duration;
		int x = back ? -1 : 1; 

		Game.renderingShiftX = -dx * Game.getVirtualWidth() * x;
		oldView.render();

		Game.renderingShiftX = (1 - dx) * Game.getVirtualWidth() * x;
		newView.render();

		Game.renderingShiftX = tmp;
	}
}
