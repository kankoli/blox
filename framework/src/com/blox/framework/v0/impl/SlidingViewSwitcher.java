package com.blox.framework.v0.impl;

import com.blox.framework.v0.util.Game;

public class SlidingViewSwitcher extends ViewSwitcher {
	public SlidingViewSwitcher(float duration) {
		super(duration);
	}

	@Override
	protected void renderSwitching(boolean back) {
		float dx = elapsed / duration;
		int x = back ? -1 : 1;

		Game.setRenderingShift(-dx * Game.getVirtualWidth() * x, 0, false);
		oldView.render();

		Game.setRenderingShift((1 - dx) * Game.getVirtualWidth() * x, 0, false);
		newView.render();

		Game.resetRenderingShift();
	}
}
