package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.util.Game;

public class SlidingViewSwitcher extends ViewSwitcher {
	public SlidingViewSwitcher(float duration) {
		super(duration);
	}

	@Override
	protected void renderSwitching(boolean back) {
		float dx = elapsed / duration;
		float x = back ? -1f : 1f;

		Game.pushRenderingShift(-dx * Game.getVirtualWidth() * x, 0, false);
		oldView.draw();
		Game.popRenderingShift();

		Game.pushRenderingShift((1 - dx) * Game.getVirtualWidth() * x, 0, false);
		newView.draw();
		Game.popRenderingShift();
	}
}
