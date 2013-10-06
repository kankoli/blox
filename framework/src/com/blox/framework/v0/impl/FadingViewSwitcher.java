package com.blox.framework.v0.impl;

import com.blox.framework.v0.util.Game;

public class FadingViewSwitcher extends ViewSwitcher {

	public FadingViewSwitcher(float duration) {
		super(duration);
	}

	@Override
	protected void renderSwitching(boolean back) {
		float alpha = elapsed / duration;

		Game.renderingAlpha = 1 - alpha;
		oldView.render();

		Game.renderingAlpha = alpha;
		newView.render();

		Game.renderingAlpha = 1;
	}
}